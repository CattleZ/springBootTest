package com.example.webtest;

import com.example.designmodel.prototype.EnemyPlane;
import org.junit.jupiter.api.Test;
class WebTestApplicationTests {

    @Test
    void contextLoads() throws CloneNotSupportedException {
        EnemyPlane enemyPlane = new EnemyPlane(10);
        EnemyPlane enemyPlane1 = (EnemyPlane) enemyPlane.clone();
        System.out.println(enemyPlane1.equals(enemyPlane));
        System.out.println(enemyPlane1 == enemyPlane);
    }
}
