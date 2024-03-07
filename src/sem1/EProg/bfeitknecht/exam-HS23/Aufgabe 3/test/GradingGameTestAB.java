import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GradingGameTestAB {
	
	/********** a) **********/
	
	@Test
	public void testAACreateHuman() {
		Game game = new Game();
		Human h1 = game.createHuman(105, 20);
		
		assertNotNull(h1);
		
		assertEquals(105, h1.getHealth());
		assertEquals(20, h1.getPosition());
	}
	
	@Test
	public void testAACreateWarrior() {
		Game game = new Game();
		Human h1 = game.createWarrior(120, 35);
		
		assertNotNull(h1);
		
		assertEquals(120, h1.getHealth());
		assertEquals(35, h1.getPosition());
	}
	
	@Test
	public void testABasicHumans01() {
		Game game = new Game();
		Human h1 = game.createHuman(10, 6);
		Human h2 = game.createHuman(100, 250);
		Human h3 = game.createHuman(500, 994);

		assertNotNull(h1);
		assertEquals(10, h1.getHealth());
		assertEquals(6, h1.getPosition());

		assertNotNull(h2);
		assertEquals(100, h2.getHealth());
		assertEquals(250, h2.getPosition());

		assertNotNull(h3);
		assertEquals(500, h3.getHealth());
		assertEquals(994, h3.getPosition());

		h1.scheduleAction(Action.ATTACK);
		h2.scheduleAction(Action.SUMMON);

		game.advanceTurn();

		assertEquals(10, h1.getHealth());
		assertEquals(6, h1.getPosition());

		assertEquals(100, h2.getHealth());
		assertEquals(250, h2.getPosition());

		assertEquals(500, h3.getHealth());
		assertEquals(994, h3.getPosition());
	}

	@Test
	public void testAException01() {
		Game game = new Game();
		Human human = game.createHuman(100, 10);

		human.scheduleAction(Action.ATTACK);
		assertThrows(IllegalArgumentException.class, () -> human.scheduleAction(Action.ATTACK));
		assertThrows(IllegalArgumentException.class, () -> human.scheduleAction(Action.SUMMON));
	}

	@Test
	public void testAException02() {
		Game game = new Game();
		Human human = game.createWarrior(100, 10);

		human.scheduleAction(Action.SUMMON);
		game.advanceTurn();

		assertThrows(IllegalArgumentException.class, () -> human.scheduleAction(Action.ATTACK));
		assertThrows(IllegalArgumentException.class, () -> human.scheduleAction(Action.SUMMON));
	}

	@Test
	public void testAException03Given() {
		Game game = new Game();
		Human h1 = game.createHuman(10, 9);
		Human h2 = game.createWarrior(1, 10);
		Human h3 = game.createWarrior(9, 11);

		h2.scheduleAction(Action.ATTACK); // h1 and h3 not alive after action

		game.advanceTurn();

		assertThrows(IllegalArgumentException.class, () -> h1.scheduleAction(Action.ATTACK));
		assertThrows(IllegalArgumentException.class, () -> h1.scheduleAction(Action.SUMMON));
		assertThrows(IllegalArgumentException.class, () -> h3.scheduleAction(Action.ATTACK));
		assertThrows(IllegalArgumentException.class, () -> h3.scheduleAction(Action.SUMMON));
	}

	@Test
	public void testAWarriorAttack01Given() {
		Game game = new Game();
		Human h1 = game.createWarrior(100, 9);
		Human h2 = game.createWarrior(100, 10);
		Human h3 = game.createWarrior(100, 10);
		Human h4 = game.createWarrior(100, 11);
		Human h5 = game.createWarrior(100, 12);

		h1.scheduleAction(Action.ATTACK);
		h2.scheduleAction(Action.ATTACK);

		// action is not yet executed
		assertEquals(100, h1.getHealth());
		assertEquals(100, h2.getHealth());
		assertEquals(100, h3.getHealth());
		assertEquals(100, h4.getHealth());
		assertEquals(100, h5.getHealth());

		game.advanceTurn();

		// attacks are executed
		assertEquals(90, h1.getHealth()); // hit by h2
		assertEquals(90, h2.getHealth()); // hit by h1
		assertEquals(90, h3.getHealth()); // hit by h1
		assertEquals(90, h4.getHealth()); // hit by h2
		assertEquals(100, h5.getHealth());
	}
	
	@Test
	public void testAWarriorAttack02() {
		Game game = new Game();
		Human h1 = game.createWarrior(105, 9);
		Human h2 = game.createWarrior(121, 10);
		
		h1.scheduleAction(Action.ATTACK);
		game.advanceTurn();
		
		assertEquals(105, h1.getHealth());
		assertEquals(111, h2.getHealth());
	}

	@Test
	public void testAWarriorAttack03() {
		Game game = new Game();

		Human h1 = game.createWarrior(50, 994);
		Human h2 = game.createWarrior(50, 993);
		Human h3 = game.createWarrior(20, 6);
		Human h4 = game.createWarrior(21, 7);

		h1.scheduleAction(Action.ATTACK);
		h3.scheduleAction(Action.ATTACK);

		assertEquals(50, h1.getHealth());
		assertEquals(50, h2.getHealth());
		assertEquals(20, h3.getHealth());
		assertEquals(21, h4.getHealth());

		game.advanceTurn();

		assertEquals(50, h1.getHealth());
		assertEquals(40, h2.getHealth());
		assertEquals(20, h3.getHealth());
		assertEquals(11, h4.getHealth());

		h3.scheduleAction(Action.ATTACK);
		h2.scheduleAction(Action.ATTACK);

		assertEquals(50, h1.getHealth());
		assertEquals(40, h2.getHealth());
		assertEquals(20, h3.getHealth());
		assertEquals(11, h4.getHealth());

		game.advanceTurn();

		assertEquals(40, h1.getHealth());
		assertEquals(40, h2.getHealth());
		assertEquals(20, h3.getHealth());
		assertEquals(1, h4.getHealth());
	}

	@Test
	public void testAWarriorAttack04() {
		Game game = new Game();

		Human w1 = game.createWarrior(10, 10);
		Human w2 = game.createWarrior(10, 11);

		w1.scheduleAction(Action.ATTACK);
		w2.scheduleAction(Action.ATTACK);
		game.advanceTurn();

		assertEquals(0, w2.getHealth()); // w2 not alive after w1's action
		assertEquals(10, w1.getHealth());
	}

	@Test
	public void testAWarriorAttack05() {
		Game game = new Game();

		Human w1 = game.createWarrior(20, 50);
		Human w2 = game.createWarrior(20, 50);
		Human w3 = game.createWarrior(21, 51);

		w1.scheduleAction(Action.ATTACK);
		w2.scheduleAction(Action.ATTACK);
		game.advanceTurn();

		assertEquals(20, w1.getHealth());
		assertEquals(20, w2.getHealth());
		assertEquals(1, w3.getHealth());
	}

	@Test
	public void testAWarriorAttack06() {
		Game game = new Game();

		Human h1 = game.createWarrior(200, 20);

		Human h2 = game.createHuman(100, 19);
		Human h3 = game.createWarrior(200, 19);
		Human h4 = game.createWarrior(20, 21);

		h1.scheduleAction(Action.ATTACK);
		game.advanceTurn();

		assertEquals(200, h1.getHealth());
		assertEquals(90, h2.getHealth());
		assertEquals(190, h3.getHealth());
		assertEquals(10, h4.getHealth());
	}

	@Test
	public void testAWarriorSummon01Given() {
		Game game = new Game();
		Human h1 = game.createWarrior(100, 9);
		Human h2 = game.createWarrior(100, 10);

		h1.scheduleAction(Action.SUMMON);

		game.advanceTurn();

		assertEquals(100, h1.getHealth());
		assertEquals(100, h2.getHealth());

		h2.scheduleAction(Action.SUMMON);

		game.advanceTurn();

		assertEquals(95, h1.getHealth());
		assertEquals(100, h2.getHealth());

		game.advanceTurn();

		assertEquals(95, h1.getHealth());
		assertEquals(95, h2.getHealth());
	}

	@Test
	public void testAWarriorSummon02() {
		Game game = new Game();

		Human h1 = game.createWarrior(50, 994);
		Human h2 = game.createWarrior(50, 993);
		Human h3 = game.createWarrior(20, 6);
		Human h4 = game.createWarrior(20, 7);

		h1.scheduleAction(Action.SUMMON);
		h3.scheduleAction(Action.SUMMON);
		game.advanceTurn();

		h2.scheduleAction(Action.SUMMON);
		game.advanceTurn();

		game.advanceTurn();

		assertEquals(45, h1.getHealth());
		assertEquals(45, h2.getHealth());
		assertEquals(15, h3.getHealth());
		assertEquals(20, h4.getHealth());
	}

	@Test
	public void testAWarriorSummon03() {
		Game game = new Game();

		Human h1 = game.createWarrior(100, 300);
		Human h2 = game.createWarrior(100, 300);
		Human h3 = game.createWarrior(100, 300);

		h1.scheduleAction(Action.SUMMON);
		game.advanceTurn();

		assertEquals(100, h1.getHealth());
		assertEquals(100, h2.getHealth());
		assertEquals(100, h3.getHealth());

		game.advanceTurn();

		assertEquals(95, h1.getHealth());
		assertEquals(100, h2.getHealth());
		assertEquals(100, h3.getHealth());

		h3.scheduleAction(Action.SUMMON);
		game.advanceTurn();

		assertEquals(95, h1.getHealth());
		assertEquals(100, h2.getHealth());
		assertEquals(100, h3.getHealth());

		game.advanceTurn();

		assertEquals(95, h1.getHealth());
		assertEquals(100, h2.getHealth());
		assertEquals(95, h3.getHealth());
	}

	@Test
	public void testAWarriorZMixed01() {
		Game game = new Game();

		Human h1 = game.createWarrior(20, 10);
		Human h2 = game.createWarrior(10, 11);

		h1.scheduleAction(Action.SUMMON);
		game.advanceTurn();
		assertEquals(20, h1.getHealth());
		game.advanceTurn();
		assertEquals(15, h1.getHealth());

		h2.scheduleAction(Action.SUMMON);
		h1.scheduleAction(Action.ATTACK);

		game.advanceTurn();

		assertEquals(15, h1.getHealth());
		assertEquals(0, h2.getHealth());

		game.advanceTurn();

		// Make sure that the summon was not executed, since h2 is not alive
		assertEquals(15, h1.getHealth());
		assertEquals(0, h2.getHealth());
	}

	@Test
	public void testAWarriorZMixed02() {
		Game game = new Game();

		Human h1 = game.createWarrior(20, 10);
		Human h2 = game.createWarrior(20, 10);
		Human h3 = game.createWarrior(16, 11);
		Human h4 = game.createWarrior(10, 14);
		Human h5 = game.createWarrior(15, 15);


		h1.scheduleAction(Action.ATTACK);
		h3.scheduleAction(Action.SUMMON);
		h4.scheduleAction(Action.ATTACK);
		h5.scheduleAction(Action.ATTACK);

		game.advanceTurn();

		assertEquals(20, h1.getHealth());
		assertEquals(20, h2.getHealth());
		assertEquals(6, h3.getHealth());
		//h4 first attacks h5 before h5's action
		assertEquals(5, h5.getHealth());

		game.advanceTurn();

		assertEquals(20, h1.getHealth());
		assertEquals(20, h2.getHealth());
		assertEquals(1, h3.getHealth());
		assertEquals(5, h5.getHealth());		
	}

	
	/********** b) **********/
	
	@Test
	public void testBACreateCleric() {
		Game game = new Game();
		Human h1 = game.createCleric(200, 20);
		
		assertNotNull(h1);
		
		assertEquals(200, h1.getHealth());
		assertEquals(20, h1.getPosition());
	}
	
	@Test
	public void testBACreateGeneral() {
		Game game = new Game();
		Human h1 = game.createGeneral(150, 15);
		
		assertNotNull(h1);
		
		assertEquals(150, h1.getHealth());
		assertEquals(15, h1.getPosition());
	}
	
	@Test
	public void testBAll01Given() {
		Game game = new Game();
		Human h1 = game.createCleric(10, 9);
		Human h2 = game.createWarrior(1, 10);
		Human h3 = game.createGeneral(9, 11);

		h2.scheduleAction(Action.ATTACK); // h1 and h3 not alive after action

		game.advanceTurn();

		assertThrows(IllegalArgumentException.class, () -> h1.scheduleAction(Action.ATTACK));
		assertThrows(IllegalArgumentException.class, () -> h1.scheduleAction(Action.SUMMON));
		assertThrows(IllegalArgumentException.class, () -> h3.scheduleAction(Action.ATTACK));
		assertThrows(IllegalArgumentException.class, () -> h3.scheduleAction(Action.SUMMON));
	}
		
	/** Cleric **/
	
	//Cleric Attack	
	
	@Test
	public void testBClericAttack01() {
		Game game = new Game();
		
		Human h1 = game.createCleric(152, 6);
		Human h2 = game.createCleric(154, 7);
		Human h3 = game.createCleric(180, 8);
		Human h4 = game.createCleric(140, 9);
		
		h2.scheduleAction(Action.ATTACK);		
		game.advanceTurn();
		
		assertEquals(149, h1.getHealth());
		assertEquals(154, h2.getHealth());
		assertEquals(177, h3.getHealth());
		assertEquals(140, h4.getHealth());		
	}
	
	@Test
	public void testBClericAttack02() {
		Game game = new Game();

		Human h1 = game.createCleric(101, 12);
		Human h2 = game.createCleric(102, 15);
		Human h3 = game.createCleric(103, 15);
		Human h4 = game.createCleric(104, 15);
		Human h5 = game.createCleric(105, 14);
		Human h6 = game.createCleric(106, 13);
		Human h7 = game.createCleric(107, 13);
		Human h8 = game.createCleric(108, 25);

		
		h5.scheduleAction(Action.ATTACK);
		game.advanceTurn();
		
		assertEquals(101, h1.getHealth());
		assertEquals(99,  h2.getHealth());
		assertEquals(100, h3.getHealth());
		assertEquals(101, h4.getHealth());		
		assertEquals(105, h5.getHealth());		
		assertEquals(103, h6.getHealth());		
		assertEquals(104, h7.getHealth());
		assertEquals(108, h8.getHealth());
	}
	
	@Test
	public void testBClericAttack03() {
		Game game = new Game();
		
		Human h1 = game.createCleric(80,  12);
		Human h2 = game.createCleric(102, 14);
		Human h3 = game.createCleric(103, 15);
		Human h4 = game.createCleric(104, 16);

		h2.scheduleAction(Action.ATTACK);
		h3.scheduleAction(Action.ATTACK);
		game.advanceTurn();
		
		assertEquals(80, h1.getHealth());
		assertEquals(99, h2.getHealth());
		assertEquals(100, h3.getHealth());
		assertEquals(101, h4.getHealth());
	}
	
	//Cleric Summon
	
	@Test
	public void testBClericSummon01() {
		Game game = new Game();
		
		Human h1 = game.createCleric(80,  11);
		Human h2 = game.createCleric(80,  12);
		Human h3 = game.createCleric(80,  13);
		Human h4 = game.createCleric(102, 14);
		Human h5 = game.createCleric(104, 15);
		Human h6 = game.createCleric(104, 16);
		
		Human h7 = game.createCleric(104, 17);
		
		Human h8 = game.createCleric(104, 18);
		Human h9 = game.createCleric(104, 19);
		Human h10 = game.createCleric(104, 20);
		Human h11 = game.createCleric(104, 21);
		Human h12 = game.createCleric(104, 22);
		Human h13 = game.createCleric(104, 23);

		
		h7.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		game.advanceTurn();
		game.advanceTurn();
		
		assertEquals(11,  h1.getPosition());
		
		assertEquals(17,  h2.getPosition());
		assertEquals(17,  h3.getPosition());
		assertEquals(17, h4.getPosition());
		
		assertEquals(15, h5.getPosition());		
		assertEquals(16, h6.getPosition());		
		
		assertEquals(17, h7.getPosition());
		
		assertEquals(18, h8.getPosition());
		assertEquals(19, h9.getPosition());
		
		assertEquals(17, h10.getPosition());
		assertEquals(17, h11.getPosition());
		assertEquals(17, h12.getPosition());
		
		assertEquals(23, h13.getPosition());
	}
	
	@Test
	public void testBClericSummon02() {
		Game game = new Game();
		
		Human h1 = game.createCleric(50, 10);
		Human h2 = game.createCleric(50, 13);
		Human h3 = game.createCleric(50, 16);
		
		h1.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		
		assertEquals(10, h1.getPosition());
		assertEquals(13, h2.getPosition());
		assertEquals(16, h3.getPosition());
		
		game.advanceTurn();

		assertEquals(10, h1.getPosition());
		assertEquals(13, h2.getPosition());
		assertEquals(16, h3.getPosition());

		game.advanceTurn();
		
		assertEquals(10, h1.getPosition());
		assertEquals(10, h2.getPosition());
		assertEquals(16, h3.getPosition());
	}
	
	@Test
	public void testBClericSummon03() {
		Game game = new Game();
		
		Human h1 = game.createCleric(50, 22);
		Human h2 = game.createCleric(50, 23);
		Human h3 = game.createCleric(50, 19);
		
		Human i1 = game.createCleric(50, 45);
		Human i2 = game.createCleric(50, 44);
		Human i3 = game.createCleric(50, 42);
		
		h1.scheduleAction(Action.SUMMON);
		i1.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		game.advanceTurn();
		game.advanceTurn();
		
		assertEquals(22, h1.getPosition());
		assertEquals(23, h2.getPosition());
		assertEquals(22, h3.getPosition());
		
		assertEquals(45, i1.getPosition());
		assertEquals(44, i2.getPosition());
		assertEquals(45, i3.getPosition());
	}

	@Test
	public void testBClericSummon04() {
		Game game = new Game();
		
		Human h1 = game.createCleric(50, 17);
		Human h2 = game.createCleric(50, 35);
		Human h3 = game.createCleric(50, 19);
		Human h4 = game.createCleric(50, 21);
		Human h5 = game.createCleric(50, 22);
		Human h6 = game.createCleric(50, 24);


		h1.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		
		h3.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		
		game.advanceTurn();
		
		assertEquals(17, h1.getPosition());
		assertEquals(35, h2.getPosition());
		assertEquals(19, h3.getPosition());
		assertEquals(17, h4.getPosition());
		assertEquals(17, h5.getPosition());
		assertEquals(24, h6.getPosition());
		
		game.advanceTurn();

		assertEquals(17, h1.getPosition());
		assertEquals(35, h2.getPosition());
		assertEquals(19, h3.getPosition());
		assertEquals(17, h4.getPosition());
		assertEquals(17, h5.getPosition());
		assertEquals(19, h6.getPosition());
	}
	
	// Cleric Attack + Summon
	
	@Test
	public void testBClericZAttackSummon01() {		
		Game game = new Game();
		
		Human h1 = game.createCleric(2, 11);
		Human h2 = game.createCleric(205, 12);
		Human h3 = game.createCleric(210, 16);
		
		h3.scheduleAction(Action.SUMMON);		
		h2.scheduleAction(Action.ATTACK);
		
		game.advanceTurn();
		
		assertTrue(h1.getHealth() <= 0);
		assertEquals(205, h2.getHealth());
		assertEquals(210, h3.getHealth());
		
		assertEquals(11, h1.getPosition());
		assertEquals(12, h2.getPosition());
		assertEquals(16, h3.getPosition());
		
		game.advanceTurn();
		game.advanceTurn();
		
		assertEquals(11, h1.getPosition()); //not summoned, since h1 is not alive
		assertEquals(16, h2.getPosition());
		assertEquals(16, h2.getPosition());
	}

	
	/** General **/
	
	//General Attack
	
	@Test 
	public void testBGeneralAttack01GivenFix() {
		Game game = new Game();
		Human h1 = game.createHuman(100, 6);
		Human h2 = game.createHuman(100, 7);
		Human h3 = game.createHuman(100, 7);
		Human m1 = game.createGeneral(100, 9);
		Human h4 = game.createHuman(100, 9);
		Human h5 = game.createHuman(100, 21);
		
		m1.scheduleAction(Action.ATTACK);
		game.advanceTurn();
		
		assertEquals(85, h1.getHealth());
		assertEquals(82, h2.getHealth());
		assertEquals(82, h3.getHealth());
		assertEquals(80, h4.getHealth());
		assertEquals(92, h5.getHealth());
	}

	@Test 
	public void testBGeneralAttack02() {
		Game game = new Game();
		Human h = game.createGeneral(70, 10);
		Human general = game.createGeneral(95, 14);
		
		general.scheduleAction(Action.ATTACK);		
		game.advanceTurn();
		
		assertEquals(54, h.getHealth());
	}
	
	@Test
	public void testBGeneralAttack03() {
		Game game = new Game();
		Human h1 = game.createGeneral(80, 23);
		Human h2 = game.createGeneral(60, 29);
		Human h3 = game.createGeneral(60, 32);
		Human general = game.createGeneral(50, 35);
		
		general.scheduleAction(Action.ATTACK);
		game.advanceTurn();
		
		assertEquals(74, h1.getHealth());
		assertEquals(47, h2.getHealth());
		assertEquals(43, h3.getHealth());		
	}
	
	@Test
	public void testBGeneralAttack04() {
		Game game = new Game();
		Human h1 = game.createGeneral(120, 64);
		Human h2 = game.createGeneral(120, 50);
		Human h3 = game.createGeneral(120, 48);
		Human general = game.createGeneral(300, 45);
		Human h4 = game.createGeneral(250,25);
		Human h5 = game.createGeneral(250,20);
		
		general.scheduleAction(Action.ATTACK);
		game.advanceTurn();
		
		assertEquals(120, h1.getHealth());
		assertEquals(106, h2.getHealth());
		assertEquals(103, h3.getHealth());
		
		assertEquals(250, h4.getHealth());
		assertEquals(250, h5.getHealth());
	}
	
	@Test
	public void testBGeneralAttack05() {
		Game game = new Game();
		Human general = game.createGeneral(20, 90);
		Human h1 = game.createGeneral(45, 90);
		Human h2 = game.createGeneral(49, 89);
		Human h3 = game.createGeneral(49, 91);
		
		general.scheduleAction(Action.ATTACK);
		game.advanceTurn();
		
		assertEquals(25, h1.getHealth());
		assertEquals(30, h2.getHealth());
		assertEquals(30, h3.getHealth());
	}
	
	@Test
	public void testBGeneralAttack06() {
		Game game = new Game();
		
		Human general = game.createGeneral(130, 45);
				
		Human h1 = game.createGeneral(130, 35);
		Human h2 = game.createGeneral(52, 36);
		Human h3 = game.createGeneral(33, 36);
		Human h4 = game.createGeneral(69, 37);
		Human h5 = game.createGeneral(82, 37);
		Human h6 = game.createGeneral(141, 38);
		
		Human i1 = game.createGeneral(84, 49);
		Human i2 = game.createGeneral(95, 50);
		Human i3 = game.createGeneral(72, 51);
		Human i4 = game.createGeneral(88, 51);
		
		general.scheduleAction(Action.ATTACK);
		game.advanceTurn();
		
		assertEquals(125, h1.getHealth());
		assertEquals(44,  h2.getHealth());
		assertEquals(25,  h3.getHealth());
		assertEquals(58,  h4.getHealth());
		assertEquals(71,  h5.getHealth());
		assertEquals(128, h6.getHealth());

		assertEquals(68, i1.getHealth());
		assertEquals(81, i2.getHealth());
		assertEquals(60, i3.getHealth());
		assertEquals(76, i4.getHealth());
	}
	
	//General Summon
	
	@Test
	public void testBGeneralSummon01GivenFix() {
		Game game = new Game();
		Human h1 = game.createHuman(  101,  6); // score 71
		Human h2 = game.createHuman(   90,  7); // score 70
		Human h3 = game.createHuman(  100,  7); // score 80
		Human m1 = game.createGeneral(100,  9);
		Human h4 = game.createHuman(   50,  9); // score 50
		Human h5 = game.createHuman(   70, 14); // score 20
		Human h6 = game.createHuman( 1000, 15); // too far
		
		m1.scheduleAction(Action.SUMMON);
		game.advanceTurn();
		
		assertEquals(6, h1.getPosition());
		assertEquals(9, h2.getPosition()); // summoned by m1
		assertEquals(7, h3.getPosition());
		assertEquals(9, h4.getPosition());
		assertEquals(14, h5.getPosition());
		assertEquals(15, h6.getPosition());
	}
	
	@Test
	public void testBGeneralSummon02() {
		Game game = new Game();
		Human general = game.createGeneral(200, 10);
		Human h1 = game.createGeneral(102, 9);
		Human h2 = game.createGeneral(89, 12);
		Human h3 = game.createGeneral(87, 11);
		
		general.scheduleAction(Action.SUMMON);
		game.advanceTurn();
		
		assertEquals(9, h1.getPosition());
		assertEquals(12, h2.getPosition()); 
		assertEquals(10, h3.getPosition());
	}
	
	@Test
	public void testBGeneralSummon03() {
		Game game = new Game();
		Human general = game.createGeneral(120,  9);
		
		Human h1 = game.createGeneral(   70,    10);
		Human h2 = game.createGeneral(   90,    14);
		Human h3 = game.createGeneral( 3000,    17); // too far
		Human h4 = game.createGeneral(  121,     6);
		Human h5 = game.createGeneral(  110,     7);
		Human h6 = game.createGeneral(  120,     8);

		
		general.scheduleAction(Action.SUMMON);
		game.advanceTurn();
		
		assertEquals(10, h1.getPosition());
		assertEquals(14, h2.getPosition());
		assertEquals(17, h3.getPosition());
		assertEquals(6,  h4.getPosition());
		assertEquals(9,  h5.getPosition()); 
		assertEquals(8,  h6.getPosition());
	}
		
	@Test
	public void testBGeneralSummon04() {
		Game game = new Game();
		
		Human h1 = game.createGeneral(110, 7);
		Human h2 = game.createGeneral(162, 8);
		Human h3 = game.createGeneral(200, 9);
		
		Human general = game.createGeneral(15, 10);
		
		Human h4 = game.createGeneral(168, 15);
		Human h5 = game.createGeneral(135, 12);

		general.scheduleAction(Action.SUMMON);
		game.advanceTurn();
		
		assertEquals(7, h1.getPosition());
		assertEquals(8, h2.getPosition());
		assertEquals(9, h3.getPosition());
		assertEquals(10, h4.getPosition());
		assertEquals(12, h5.getPosition());
	}
	
	@Test
	public void testBGeneralSummon05() {
		Game game = new Game();
		
		Human general = game.createGeneral(200, 20);

		Human h1 = game.createGeneral(120, 19);
		Human h2 = game.createGeneral(170, 18);

		
		Human o1 = game.createGeneral(320, 27);
		Human o2 = game.createGeneral(330, 10);

		
		Human h3 = game.createGeneral(220, 21);
		Human h4 = game.createGeneral(170, 24);
		Human h5 = game.createGeneral(180, 16);
		Human h6 = game.createGeneral(250, 17);
		Human h7 = game.createGeneral(240, 21);

		Human o3 = game.createGeneral(220, 11);
		Human o4 = game.createGeneral(230, 10);
		
		general.scheduleAction(Action.SUMMON);
		game.advanceTurn();
		
		assertEquals(19, h1.getPosition());
		assertEquals(20, h2.getPosition());
		assertEquals(21, h3.getPosition());
		assertEquals(24, h4.getPosition());
		assertEquals(16, h5.getPosition());
		assertEquals(17, h6.getPosition());
		assertEquals(21, h7.getPosition());
		
		assertEquals(27, o1.getPosition());
		assertEquals(10, o2.getPosition());
		assertEquals(11, o3.getPosition());
		assertEquals(10, o4.getPosition());
	}
	
	@Test
	public void testBGeneralSummon06() {
		Game game = new Game();
		
		Human general1 = game.createGeneral(200, 20);
		Human general2 = game.createGeneral(200, 21);
		
		Human h1 = game.createGeneral(400, 18);
		Human h2 = game.createGeneral(800, 19);
		
		general1.scheduleAction(Action.SUMMON);
		general2.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		
		assertEquals(21, h1.getPosition());
		assertEquals(19, h2.getPosition());					
	}
	
	// Mixed
	
	@Test
	public void testBZMixed01GivenFix() {
		Game game = new Game();
		Human h1 = game.createWarrior(100, 6);
		Human h2 = game.createCleric(100, 11);
		Human h3 = game.createWarrior(100, 12);
		Human h4 = game.createWarrior(100, 16);
		
		h2.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		game.advanceTurn();
		
		h1.scheduleAction(Action.ATTACK);
		h3.scheduleAction(Action.ATTACK);
		
		game.advanceTurn();
		
		assertEquals(11, h1.getPosition());
		assertEquals(11, h4.getPosition());
		
		assertEquals(90, h1.getHealth());
		assertEquals(90, h2.getHealth());
		assertEquals(90, h3.getHealth());
		assertEquals(90, h4.getHealth());
	}
	
	@Test
	public void testBZMixed02() {
		Game game = new Game();
		Human c1 = game.createCleric(200, 25);
		Human c2 = game.createCleric(250, 26);
		Human c3 = game.createCleric(260, 27);
		Human general = game.createGeneral(200, 30);
		
				
		c3.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		game.advanceTurn();				
		
		c2.scheduleAction(Action.ATTACK);
		general.scheduleAction(Action.ATTACK);
		
		game.advanceTurn();
		
		assertEquals(27, general.getPosition());
			
		assertEquals(180, c1.getHealth());
		assertEquals(25, c1.getPosition());
	}
	
}
