package com.example.SpringSample.recipe.domain.login.model;

import javax.validation.GroupSequence;

// 【6-3-4】グループの実行順序　バリデーションをグルーピ実行するには実行順序を設定するインターフェースに@GroupSequence
//　を付けます。アノテーションパラメーターに各グループの.classeを指定します。左から順番にバリデーションが実行されて行きます。
@GroupSequence({ ValidGroup1.class, ValidGroup2.class, ValidGroup3.class })
public interface GroupOrder {

}
