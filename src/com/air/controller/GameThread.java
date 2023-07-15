package com.air.controller;

import java.util.List;
import java.util.Map;

import com.air.element.Boss;
import com.air.element.ElementObj;
import com.air.element.Enemy;
import com.air.element.Play;
import com.air.element.Prop;
import com.air.manager.ElementManager;
import com.air.manager.GameElement;
import com.air.manager.GameLoad;
import com.air.show.GameLevel;

public class GameThread extends Thread{
	private static int score = 0;	
	private ElementManager em;
	
	private static String propType = null;//道具类型
	public GameThread() {
		em=ElementManager.getManager();
	}
	
	@Override
	public void run() {//游戏的run方法 主线程
		while (true) {
//		游戏开始前 读进度条，加载游戏资源（场景资源）
			gameLoad();
//		游戏进行时 游戏过程中
			gameRun();
//		游戏场景结束 游戏资源回收（场景资源）
			gameOver();
			
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	/**
	 * 游戏的加载
	 */
	private void gameLoad() {
		GameLoad.loadImg();//加载图片
		GameLoad.MapLoad(GameLevel.getLevel());//传等级
//		加载主角
		GameLoad.loadPlay(GameLevel.getLevel());//可带参数，单机还是2人		
//		加载敌人NPC
		GameLoad.loadNpc(GameLevel.getLevel());
//		加载BOSS
		GameLoad.loadBoss(GameLevel.getLevel());
//		加载道具
		GameLoad.loadProp(GameLevel.getLevel());
	}

	private int gameTime=0;
	private void gameRun() {
		while (!GameLevel.flag && Play.life) {//true可以变为变量，用于控制关卡结束等
			Map<GameElement, List<ElementObj>> all = em.getGameElements();
			List<ElementObj> enemys = em.getElementsByKey(GameElement.ENEMY);//敌人
			List<ElementObj> bosses = em.getElementsByKey(GameElement.BOSS);//BOSS
			List<ElementObj> fires = em.getElementsByKey(GameElement.PLAYFILE);//玩家子弹
			List<ElementObj> enemyfires = em.getElementsByKey(GameElement.ENEMYFILE);//敌人子弹
			List<ElementObj> plays = em.getElementsByKey(GameElement.PLAY);//玩家
			List<ElementObj> props = em.getElementsByKey(GameElement.PROP);//道具
			
			moveAndUpdate(all, gameTime);//	游戏元素自动化方法
			
//			加载敌人NPC
			if(!plays.isEmpty() && gameTime%150==0) {
				GameLoad.loadNpc(GameLevel.getLevel());
			}

			ElementPK(plays,enemys,2);//玩家和敌人
			ElementPK(enemys,fires,1);//敌人和玩家子弹
			ElementPK(bosses,fires,1);//boss和玩家子弹
			ElementPK(plays, enemyfires,1);//玩家和敌人/Boss子弹	
			ElementPK(plays, props,0);//玩家和道具
//			int sc=0;			
//			if (sc!=score) {
//				System.out.println(score);
//				sc=score;
//			}
			
			if(getScore()==50*GameLevel.getLevel()) {//分数条件
				if(GameLevel.getLevel() == 6)
				{
					GameLoad.next("1");
					try {
						sleep(5000);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					System.exit(0);
				}else {
					GameLoad.next("0");
				}
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				break;
			}
			
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			gameTime++;//唯一的时间控制
		}
	}
	
	/**
	 * 
	 * @param listA 敌人 玩家
	 * @param listB 子弹
	 * @param flag 0 道具vs玩家 道具-1 玩家不变  
	 * 			   1 子弹vs敌人 子弹vs玩家 子弹-1 敌人/玩家-1 
	 * 			   2 玩家vs敌人
	 */
	private void ElementPK(List<ElementObj> listA,List<ElementObj> listB,int flag) {
		for (int i = 0; i < listA.size(); i++) {
			ElementObj character=listA.get(i);
			for (int j = 0; j < listB.size(); j++) {
				ElementObj thing=listB.get(j);					
				if (character.pk(thing)) {
					if(flag==2) {
						thing.setHp(thing.getHp()-3);
						character.setHp(character.getHp()-10);						
						if (character.getHp()==0) {
							character.setLive(false);
						}else if(thing.getHp()==0) {
							thing.setLive(false);
							if(thing instanceof Enemy)
								score += 5; //大boss则得分多
							else 
								score += 15*GameLevel.getLevel();
						}
					}else {
						thing.setLive(false);
						if (flag==1) {//子弹和敌人&玩家
//							System.out.println(character+":"+character.getHp());
							character.setHp(character.getHp()-thing.getATK());
							
							if(character.getHp()==0) {
//								System.out.println(character instanceof Enemy);
								if(character instanceof Enemy)
									score += 5; //大boss则得分多
								else {
									if (score+15*GameLevel.getLevel()<50*GameLevel.getLevel()) {
										score += 15*GameLevel.getLevel(); 
									}else {
										score=50*GameLevel.getLevel();//如果boss加分后超过过关分数，则令分数等于过关分数
									}
									 
								}
									
								character.setLive(false);	//碰到子弹 血量无 人物死亡
							}
						}
						else if (flag==0 && thing instanceof Prop) {//道具和玩家
							GameThread.setPropType(((Prop)thing).getPropType());//得到当前碰撞道具类型
						}
						break;
					}					
				}							
			}
		}
	}


//	游戏元素自动化方法
	public void moveAndUpdate(Map<GameElement, List<ElementObj>> all,int gameTime) {
//		GameElement.values();//隐藏方法 返回值是一个数组
		for (GameElement ge : GameElement.values()) {
			List<ElementObj> list = all.get(ge);
//			for (int i = 0; i < list.size(); i++) {
			for (int i = list.size()-1; i >= 0; i--) {
				ElementObj obj=list.get(i);//读取为基类
				if (!obj.isLive()) {//如果死亡
//					list.remove(i--);
//					启动一个死亡方法（死亡动画，掉装备）
					obj.die();
					list.remove(i);
					continue;
				}
				obj.model(gameTime);
			}
		}
	}
	
	/**
	 * 游戏分数计算
	 */	
	public static int getScore() {
		return score;
	}
	
	public static void setScore(int score) {
		GameThread.score = score;
	}

	/**
	 * 游戏切换关卡
	 */
	private void gameOver() {		
//		失败界面
		if(!Play.life) {//分数条件
			GameLevel.setLevel(1);
			GameLoad.next("2");
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if(!GameLevel.flag && GameLevel.getLevel()<6) //关卡递增
			GameLevel.setLevel(GameLevel.getLevel()+1);
//		GameLevel.flag = false;
		
//		变量归0
		score = 0;
//		资源回收
		Map<GameElement,List<ElementObj>> all = em.getGameElements();
		for(GameElement ge: GameElement.values()) {
			List<ElementObj> list = all.get(ge);
			for (int i = list.size()-1; i >=0; i--) {
				list.remove(i);
			}
		}		
	}

	public static String getPropType() {
		return propType;
	}
	
	public static void setPropType(String propType) {
		GameThread.propType = propType;
	}	
	
	
}
