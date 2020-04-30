package com.example.SpringSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

// 【10-3-1】 @EnablewebSecurity セキュリティ設定用クラス
// WebSecurityConfigurerAdapterの各メソッドをオーバーライドする事でセキュリティの設定を行う事ができるようになります
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // データソース
    @Autowired
    private DataSource dataSource;

    // 【10-3-4】　パスワードの暗号化　spring2.0からパスワードは暗号化が必須になりました。
    // BCryptPasswordEncoderのインスタンスを返すように＠Beanを指定しています。
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ユーザーIDとパスワードを取得するSQL文
    private static final String USER_SQL = "SELECT"
            + "    user_id,"
            + "    password,"
            + "    true"
            + " FROM"
            + "    m_user"
            + " WHERE"
            + "    user_id = ?";

    // ユーザーのロールを取得するSQL文
    private static final String ROLE_SQL = "SELECT"
            + "    user_id,"
            + "    role"
            + " FROM"
            + "    m_user"
            + " WHERE"
            + "    user_id = ?";

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 【10-3-1】静的リソースの除外webjar css には誰でもアクセスできるようにする。
        // ・正規表現『**』でいずれかのファイル
        //静的リソースへのアクセスには、セキュリティを適用しない
        web.ignoring().antMatchers("/webjars/∗∗", "/css/∗∗");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 【10-3-1】直リンクの禁止 .antMatchers("<リンク先>").permitAll()
        // ログイン不要ページの設定
        http
            .authorizeRequests()
                .antMatchers("/webjars/**").permitAll() //webjarsへアクセス許可
                .antMatchers("/css/**").permitAll() //cssへアクセス許可
                .antMatchers("/login").permitAll() //ログインページは直リンクOK
                .antMatchers("/signup").permitAll() //ユーザー登録画面は直リンクOK
                .antMatchers("/rest/**").permitAll() // 【11-2-3】REST　はCSRF対策を無効にする
                .antMatchers("/recipelist/**").permitAll() // 【11-2-3】REST　はCSRF対策を無効にする
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN") //アドミンユーザーに許可
                .anyRequest().authenticated(); //それ以外は直リンク禁止

        //ログイン処理
        http
            .formLogin()
                .loginProcessingUrl("/login") //ログイン処理のパス
                .loginPage("/login") //ログインページの指定
                .failureUrl("/login") //ログイン失敗時の遷移先
                .usernameParameter("userId") //ログインページのユーザーID
                .passwordParameter("password") //ログインページのパスワード
                .defaultSuccessUrl("/home", true); //ログイン成功後の遷移先

        //ログアウト処理
        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //
                .logoutUrl("/logout") //ログアウトのURL
                .logoutSuccessUrl("/login"); //ログアウト成功後のURL

        //【11-2-3】RESTはCSRF対策を無効にする。
        RestMatcher csrfMatcher = new RestMatcher("/rest/**");
        RestMatcher recipe =  new RestMatcher("/recipelist/**");
        //CSRF対策
        http.csrf().requireCsrfProtectionMatcher(csrfMatcher).requireCsrfProtectionMatcher(recipe);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // ログイン処理時のユーザー情報を、DBから取得する
        // 【10-3-4】ログイン時のパスワード複合ログイン処理の時にパスワードを複合する為にpasswordEncoderをセットする事でSpringがpwを複合してくれます。
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_SQL)
                .authoritiesByUsernameQuery(ROLE_SQL)
                .passwordEncoder(passwordEncoder());
    }
}
