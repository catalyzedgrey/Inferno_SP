package com.grey.inferno.framework;

import java.awt.image.BufferedImage;

import com.grey.inferno.window.BufferedImageLoader;

public class Texture {

	SpriteSheet ps1, ps2, bs, leftLight, rightLight, leftFire, rightFire, bg, enemysprite, portalSprite, lavaSprite,
			fsprite, flyingEn, bonSprite, icess, boSS, iciss, spikess, chimerass;

	private BufferedImage block_sheet = null;

	private BufferedImage player_sheet = null;
	private BufferedImage player_sheet2 = null;

	private BufferedImage r_bullet = null;
	private BufferedImage l_bullet = null;

	private BufferedImage r_fire = null;
	private BufferedImage l_fire = null;

	private BufferedImage enemy = null;

	private BufferedImage portal = null;

	private BufferedImage lava = null;

	private BufferedImage fball = null;

	private BufferedImage flyEn = null;

	private BufferedImage bon = null;
	private BufferedImage ice = null;
	//private BufferedImage boss = null;
	private BufferedImage icicles = null;
	
	private BufferedImage spike = null;
	private BufferedImage chimera = null;

	public BufferedImage[] block = new BufferedImage[8];

	public BufferedImage[] player = new BufferedImage[9];
	public BufferedImage[] player2 = new BufferedImage[9];

	public BufferedImage[] rightbullet = new BufferedImage[6];
	public BufferedImage[] leftbullet = new BufferedImage[6];

	public BufferedImage[] rightflame = new BufferedImage[6];
	public BufferedImage[] leftflame = new BufferedImage[6];

	public BufferedImage[] movingPortal = new BufferedImage[6];

	public BufferedImage[] enemyarr = new BufferedImage[8];

	public BufferedImage[] movingLava = new BufferedImage[8];

	public BufferedImage[] fireball = new BufferedImage[2];

	public BufferedImage[] flyingEnemy = new BufferedImage[4];
	public BufferedImage[] bonfire = new BufferedImage[9];

	public BufferedImage[] icearr = new BufferedImage[53];
	//public BufferedImage[] bossarr = new BufferedImage [11];
	
	public BufferedImage [] icicarr = new BufferedImage [6];
	
	public BufferedImage[] spikearr = new BufferedImage[2];
	public BufferedImage [] chimeraarr = new BufferedImage [1];

	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();

		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/devil.png");
			player_sheet2 = loader.loadImage("/angel.png");
			r_bullet = loader.loadImage("/rightlight.png");
			l_bullet = loader.loadImage("/leftlight.png");

			r_fire = loader.loadImage("/rightfire.png");
			l_fire = loader.loadImage("/leftfire.png");

			enemy = loader.loadImage("/enemy.png");
			portal = loader.loadImage("/portal.png");

			lava = loader.loadImage("/lava.png");

			fball = loader.loadImage("/fireball.png");
			flyEn = loader.loadImage("/flyingEnemy.png");
			bon = loader.loadImage("/bonfire.png");

			ice = loader.loadImage("/ice.png");
			//boss = loader.loadImage("/boss.png");
			icicles = loader.loadImage("/icicles.png");
			
			spike = loader.loadImage("/spike4.png");
			chimera = loader.loadImage("/chimera.png");

		} catch (Exception e) {
			e.printStackTrace();
		}

		bs = new SpriteSheet(block_sheet);
		ps1 = new SpriteSheet(player_sheet);
		ps2 = new SpriteSheet(player_sheet2);
		leftLight = new SpriteSheet(l_bullet);
		rightLight = new SpriteSheet(r_bullet);
		leftFire = new SpriteSheet(l_fire);
		rightFire = new SpriteSheet(r_fire);

		lavaSprite = new SpriteSheet(lava);

		enemysprite = new SpriteSheet(enemy);
		portalSprite = new SpriteSheet(portal);

		fsprite = new SpriteSheet(fball);

		flyingEn = new SpriteSheet(flyEn);
		bonSprite = new SpriteSheet(bon);
		icess = new SpriteSheet(ice);
		//boSS = new SpriteSheet(boss);
		iciss = new SpriteSheet(icicles);
		spikess = new SpriteSheet(spike);
		chimerass = new SpriteSheet(chimera);

		getTextures();
	}

	private void getTextures() {
		block[0] = bs.grabImage(1, 1, 32, 32); // dirt
		block[1] = bs.grabImage(2, 1, 32, 32); // grass
		block[2] = bs.grabImage(3, 1, 32, 32); // lava
		block[3] = bs.grabImage(3, 1, 32, 32); // lava
		block[4] = bs.grabImage(4, 1, 32, 32); // lava
		/*
		 * block[5] = bs.grabImage(5, 1, 32, 32); block[6] = bs.grabImage(6, 1,
		 * 32, 32); block[7] = bs.grabImage(7, 1, 32, 32);
		 */

		// idle player
		player[0] = ps1.grabImage(1, 1, 32, 48);

		// right
		player[1] = ps1.grabImage(1, 3, 32, 48);
		player[2] = ps1.grabImage(2, 3, 32, 48);
		player[3] = ps1.grabImage(3, 3, 32, 48);
		player[4] = ps1.grabImage(4, 3, 32, 48);

		// left
		player[5] = ps1.grabImage(1, 2, 32, 48);
		player[6] = ps1.grabImage(2, 2, 32, 48);
		player[7] = ps1.grabImage(3, 2, 32, 48);
		player[8] = ps1.grabImage(4, 2, 32, 48);

		// player 2

		player2[0] = ps2.grabImage(1, 1, 48, 48);

		// right
		player2[1] = ps2.grabImage(1, 3, 48, 48);
		player2[2] = ps2.grabImage(2, 3, 48, 48);
		player2[3] = ps2.grabImage(3, 3, 48, 48);
		player2[4] = ps2.grabImage(4, 3, 48, 48);

		// left
		player2[5] = ps2.grabImage(1, 2, 48, 48);
		player2[6] = ps2.grabImage(2, 2, 48, 48);
		player2[7] = ps2.grabImage(3, 2, 48, 48);
		player2[8] = ps2.grabImage(4, 2, 48, 48);

		// light

		// right
		rightbullet[0] = rightLight.grabImage(1, 1, 128, 48);
		rightbullet[1] = rightLight.grabImage(2, 1, 128, 48);
		rightbullet[2] = rightLight.grabImage(3, 1, 128, 48);
		rightbullet[3] = rightLight.grabImage(1, 2, 128, 48);
		rightbullet[4] = rightLight.grabImage(2, 2, 128, 48);
		rightbullet[5] = rightLight.grabImage(3, 2, 128, 48);

		// left
		leftbullet[0] = leftLight.grabImage(1, 1, 128, 48);
		leftbullet[1] = leftLight.grabImage(2, 1, 128, 48);
		leftbullet[2] = leftLight.grabImage(3, 1, 128, 48);
		leftbullet[3] = leftLight.grabImage(1, 2, 128, 48);
		leftbullet[4] = leftLight.grabImage(2, 2, 128, 48);
		leftbullet[5] = leftLight.grabImage(3, 2, 128, 48);

		// fire
		// right

		rightflame[0] = rightFire.grabImage(1, 1, 128, 48);
		rightflame[1] = rightFire.grabImage(2, 1, 128, 48);
		rightflame[2] = rightFire.grabImage(3, 1, 128, 48);
		rightflame[3] = rightFire.grabImage(1, 2, 128, 48);
		rightflame[4] = rightFire.grabImage(2, 2, 128, 48);
		rightflame[5] = rightFire.grabImage(3, 2, 128, 48);

		// left
		leftflame[0] = leftFire.grabImage(1, 1, 128, 48);
		leftflame[1] = leftFire.grabImage(2, 1, 128, 48);
		leftflame[2] = leftFire.grabImage(3, 1, 128, 48);
		leftflame[3] = leftFire.grabImage(1, 2, 128, 48);
		leftflame[4] = leftFire.grabImage(2, 2, 128, 48);
		leftflame[5] = leftFire.grabImage(3, 2, 128, 48);

		enemyarr[0] = enemysprite.grabImage(1, 1, 52, 52);
		enemyarr[1] = enemysprite.grabImage(4, 1, 52, 52);
		enemyarr[2] = enemysprite.grabImage(4, 2, 52, 52);
		enemyarr[3] = enemysprite.grabImage(4, 3, 52, 52);
		enemyarr[4] = enemysprite.grabImage(2, 1, 52, 52);
		enemyarr[5] = enemysprite.grabImage(2, 2, 52, 52);
		enemyarr[6] = enemysprite.grabImage(2, 3, 52, 52);
		// enemyarr[7] = enemysprite.grabImage(8,3,54,49);

		movingPortal[0] = portalSprite.grabImage(1, 1, 96, 96);
		movingPortal[1] = portalSprite.grabImage(2, 1, 96, 96);
		movingPortal[2] = portalSprite.grabImage(1, 2, 96, 96);
		movingPortal[3] = portalSprite.grabImage(2, 2, 96, 96);

		movingLava[0] = lavaSprite.grabImage(1, 1, 828, 600);
		movingLava[1] = lavaSprite.grabImage(2, 1, 828, 600);
		movingLava[2] = lavaSprite.grabImage(3, 1, 828, 600);
		movingLava[3] = lavaSprite.grabImage(1, 2, 828, 600);
		movingLava[4] = lavaSprite.grabImage(1, 2, 828, 600);
		movingLava[5] = lavaSprite.grabImage(3, 2, 828, 600);
		movingLava[6] = lavaSprite.grabImage(1, 3, 828, 600);
		movingLava[7] = lavaSprite.grabImage(2, 3, 828, 600);

		fireball[0] = fsprite.grabImage(1, 1, 40, 93);
		fireball[1] = fsprite.grabImage(2, 1, 40, 93);

		flyingEnemy[0] = flyingEn.grabImage(1, 2, 92, 100);
		flyingEnemy[1] = flyingEn.grabImage(2, 2, 92, 100);
		flyingEnemy[2] = flyingEn.grabImage(1, 1, 92, 100);
		flyingEnemy[3] = flyingEn.grabImage(2, 1, 92, 100);
		

		bonfire[0] = bonSprite.grabImage(1, 1, 43, 59);
		bonfire[1] = bonSprite.grabImage(2, 1, 43, 59);
		bonfire[2] = bonSprite.grabImage(1, 2, 43, 59);
		bonfire[3] = bonSprite.grabImage(2, 2, 43, 59);
		bonfire[4] = bonSprite.grabImage(3, 1, 43, 59);
		bonfire[5] = bonSprite.grabImage(4, 1, 43, 59);
		bonfire[6] = bonSprite.grabImage(3, 2, 43, 59);
		bonfire[7] = bonSprite.grabImage(5, 1, 43, 59);
		bonfire[8] = bonSprite.grabImage(4, 2, 43, 59);
		
		
		icicarr[0] = iciss.grabImage(1, 1, 46, 37);
		icicarr[1] = iciss.grabImage(2, 1, 46, 37);
		icicarr[2] = iciss.grabImage(3, 1, 46, 37);
		icicarr[3] = iciss.grabImage(1, 2, 46, 37);
		icicarr[4] = iciss.grabImage(2, 2, 46, 37);
		icicarr[5] = iciss.grabImage(3, 2, 46, 37);
		
		
		spikearr[0] = spikess.grabImage(2, 1, 79, 186);
		spikearr[1] = spikess.grabImage(1, 1, 79, 186);
		
		chimeraarr[0] = chimerass.grabImage(1, 1, 176, 128);
		
		/*
		 * flyingEnemy[4] = flyingEn.grabImage(1, 4, 65, 65); flyingEnemy[5] =
		 * flyingEn.grabImage(2, 4, 65, 65); flyingEnemy[6] =
		 * flyingEn.grabImage(3, 4, 65, 65); flyingEnemy[7] =
		 * flyingEn.grabImage(4, 4, 65, 65);
		 */
		
/*		icearr[0] = icess.grabImage (1, 1, 129, 128);
		icearr[1] = icess.grabImage (2, 1, 129, 128);
		icearr[2] = icess.grabImage (3, 1, 129, 128);
		icearr[3] = icess.grabImage (4, 1, 129, 128);
		icearr[4] = icess.grabImage (5, 1, 129, 128);
		icearr[5] = icess.grabImage (6, 1, 129, 128);
		icearr[6] = icess.grabImage (7, 1, 129, 128);
		
		icearr[7] = icess.grabImage (1, 2, 129, 128);
		icearr[8] = icess.grabImage (2, 2, 129, 128);
		icearr[9] = icess.grabImage (3, 2, 129, 128);
		icearr[10] = icess.grabImage(4, 2, 129, 128);
		icearr[11] = icess.grabImage(5, 2, 129, 128);
		icearr[12] = icess.grabImage(6, 2, 129, 128);
		icearr[13] = icess.grabImage(7, 2, 129, 128);
		
		icearr[14] = icess.grabImage(1, 3, 129, 128);
		icearr[15] = icess.grabImage(2, 3, 129, 128);
		icearr[16] = icess.grabImage(3, 3, 129, 128);
		icearr[17] = icess.grabImage(4, 3, 129, 128);
		icearr[18] = icess.grabImage(5, 3, 129, 128);
		icearr[19] = icess.grabImage(6, 3, 129, 128);
		icearr[20] = icess.grabImage(7, 3, 129, 128);
		
		
		icearr[21] = icess.grabImage(1, 3, 129, 128);
		icearr[22] = icess.grabImage(2, 3, 129, 128);
		icearr[23] = icess.grabImage(3, 3, 129, 128);
		icearr[24] = icess.grabImage(4, 3, 129, 128);
		icearr[25] = icess.grabImage(5, 3, 129, 128);
		icearr[26] = icess.grabImage(6, 3, 129, 128);
		icearr[27] = icess.grabImage(7, 3, 129, 128);
		
		
		icearr[28] = icess.grabImage(1, 4, 129, 128);
		icearr[29] = icess.grabImage(2, 4, 129, 128);
		icearr[30] = icess.grabImage(3, 4, 129, 128);
		icearr[31] = icess.grabImage(4, 4, 129, 128);
		icearr[32] = icess.grabImage(5, 4, 129, 128);
		icearr[33] = icess.grabImage(6, 4, 129, 128);
		icearr[34] = icess.grabImage(7, 4, 129, 128);
		
		
		icearr[35] = icess.grabImage(1, 5, 129, 128);
		icearr[36] = icess.grabImage(2, 5, 129, 128);
		icearr[37] = icess.grabImage(3, 5, 129, 128);
		icearr[38] = icess.grabImage(4, 5, 129, 128);
		icearr[39] = icess.grabImage(5, 5, 129, 128);
		icearr[40] = icess.grabImage(6, 5, 129, 128);
		icearr[41] = icess.grabImage(7, 5, 129, 128);
		
		
		icearr[42] = icess.grabImage(1, 6, 129, 128);
		icearr[43] = icess.grabImage(2, 6, 129, 128);
		icearr[44] = icess.grabImage(3, 6, 129, 128);
		icearr[45] = icess.grabImage(4, 6, 129, 128);
		icearr[46] = icess.grabImage(5, 6, 129, 128);
		icearr[47] = icess.grabImage(6, 6, 129, 128);
		
		
		icearr[48] = icess.grabImage(1, 7, 129, 128);
		icearr[49] = icess.grabImage(2, 7, 129, 128);
		icearr[50] = icess.grabImage(3, 7, 129, 128);
		icearr[51] = icess.grabImage(4, 7, 129, 128);*/
		
		
		/*bossarr[0] = boSS.grabImage(1, 1, 123, 92);
		bossarr[1] = boSS.grabImage(2, 1, 123, 92);
		bossarr[2] = boSS.grabImage(3, 1, 123, 92);
		bossarr[3] = boSS.grabImage(4, 1, 123, 92);
		bossarr[4] = boSS.grabImage(1, 2, 123, 92);
		bossarr[5] = boSS.grabImage(2, 2, 123, 92);
		bossarr[6] = boSS.grabImage(3, 2, 123, 92);
		bossarr[7] = boSS.grabImage(4, 2, 123, 92);
		bossarr[8] = boSS.grabImage(1, 3, 123, 92);
		bossarr[9] = boSS.grabImage(1, 4, 123, 92);
		bossarr[10] = boSS.grabImage(2, 4, 123, 92);*/
		
		
		
	}

	
		
		
		
		

}


