package com.example.designmodel.prototype;

/**
 * 创建一个EnemyPlane对象，并返回一个克隆对象
 */
public class EnemyPlaneFactory {

   private static EnemyPlane enemyPlane = new EnemyPlane(100);

   public static EnemyPlane getEnemyPlane() throws CloneNotSupportedException {
       EnemyPlane enemyPlaneClone = enemyPlane.clone();
       enemyPlaneClone.setX(100);
       return enemyPlaneClone;
   }

}
