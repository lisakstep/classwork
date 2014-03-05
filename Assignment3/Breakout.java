/*
 * File: Breakout.java
 * -------------------
 * Name:Lisa Stephens
 * Section Leader: ha!
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

//import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
		(WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Animation delay */
	private static final int DELAY = 50;

	/** Number of turns */
	private static final int NTURNS = 3;

	/* Method: init() */
	/** Sets up the Breakout program. */
	// First: set up the bricks to be broken
	public void init() {
		// Initialize number of turns
		turnsRemaining = NTURNS;
		// Initialize the number of bricks in the game
		brickCount = NBRICKS_PER_ROW * NBRICK_ROWS;
		score = 0;

		// Draw the bricks and paddle
		drawBricks();
		createPaddle();

		// Draw the title at the top
		GLabel title = new GLabel("BREAKOUT!!", .4*getWidth(), getHeight()*.05 );
		title.setFont(Font.SANS_SERIF);
		title.setColor(Color.BLUE);
		add(title);

		// Draw the player info label
		drawInfoLabel();

		// Start watching for mouse movement
		addMouseListeners();
	}

	private void drawInfoLabel() {
		// Draw the line that give the user turns remaining and score info
		info = new GLabel("NUMBER OF TURNS REMAINING: " + turnsRemaining + "      Score: " + score, BRICK_SEP, 4*BRICK_SEP );
		info.setFont(Font.SANS_SERIF);
		info.setColor(Color.black);
		add(info);
	}

	// Move paddle from side to side based on mouse location in relation to the center of the paddle
	public void mouseMoved (MouseEvent e){
		// Find out where the mouse is in the X direction, shift to paddle center 
		int paddleXComponent = e.getX() - PADDLE_WIDTH/2;

		//ensure paddle stays fully on screen when mouse moves off the canvas to the right
		if (paddleXComponent > (WIDTH - PADDLE_WIDTH)) {
			paddleXComponent = ( WIDTH - PADDLE_WIDTH );
		}

		//ensure paddle stays fully on screen when mouse moves off the canvas to the left
		if (paddleXComponent < 0) {
			paddleXComponent = 0;
		}

		// x component for the paddle becomes the corrected e.getX() minus the current x position, y is unchanged
		paddle.move( (paddleXComponent - paddle.getX() ),  0 );		
	}

	private void createPaddle() {
		// Let's start the paddle in the center
		paddle = new GRect( ((WIDTH/2) - (PADDLE_WIDTH/2) ), (HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT ), PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add (paddle);
	}

	private GRect createBrick(double brickX, double brickY) {
		//create a brick object
		GRect brick = new GRect (brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);
		brick.setFilled(true);
		return (brick);
	}




	private void drawBricks() {
		// Center the bricks. Calculate the left edge
		int leftBrickEdge = (WIDTH - ((BRICK_WIDTH + BRICK_SEP) * NBRICKS_PER_ROW ) - BRICK_SEP )/2 + BRICK_SEP;
		// Draw the bricks, starting at given y offset position
		int rowYCoord = BRICK_Y_OFFSET;

		for (int i = 0; i< NBRICK_ROWS; i++) {
			for (int j = 0; j< NBRICKS_PER_ROW; j++ ) {
				// Make a brick
				//brick = new GRect (  (leftBrickEdge + j*(BRICK_WIDTH + BRICK_SEP)), rowYCoord , BRICK_WIDTH, BRICK_HEIGHT);
				//brick.setFilled(true);
				brick = createBrick( (leftBrickEdge + j*(BRICK_WIDTH + BRICK_SEP)), rowYCoord);

				// Color the bricks based on the row number starting from the top assuming 10 rows
				switch (i)	{
				case (0) :	brick.setFillColor(Color.RED);
				brick.setColor(Color.RED);
				break;
				case (1) :	brick.setFillColor(Color.RED);
				brick.setColor(Color.RED);
				break;	
				case (2) :	brick.setFillColor(Color.ORANGE);
				brick.setColor(Color.ORANGE);
				break;
				case (3) :	brick.setFillColor(Color.ORANGE);
				brick.setColor(Color.ORANGE);
				break;
				case (4) :	brick.setFillColor(Color.YELLOW);
				brick.setColor(Color.YELLOW);
				break;
				case (5) :	brick.setFillColor(Color.YELLOW);
				brick.setColor(Color.YELLOW);
				break;	
				case (6) :	brick.setFillColor(Color.GREEN);
				brick.setColor(Color.GREEN);
				break;
				case (7) :	brick.setFillColor(Color.GREEN);
				brick.setColor(Color.GREEN);
				break;
				case (8) :	brick.setFillColor(Color.CYAN);
				brick.setColor(Color.CYAN);
				break;
				case (9) :	brick.setFillColor(Color.CYAN);
				brick.setColor(Color.CYAN);
				break;
				}

				add (brick);
			}

			//move down to draw the next row
			rowYCoord = rowYCoord + (BRICK_SEP + BRICK_HEIGHT);

		}
	}

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		// Create the ball and put it in the center of the canvas
		createBall();
		// Set the initial velocity of 3.0 for y and random for x directions
		initializeBallVelocity();

		while ( (turnsRemaining > 0) && ( brickCount > 0 ) ){
			// See if the ball is in the same space as the paddle or a brick
			collider = getCollidingObject();
			println("collider is " + collider);
			// Move the ball in a straight line until it reaches an edge or object
			println("move the ball");
			moveBall();
			// Make the ball bounce when it hits a canvas boundary or object
			println("handle a collision with " + collider);
			handleBallCollision();
			// check for ball collision with the paddle or a brick, then handle appropriately
		}
		// Give ending feedback. The game is over and you have exited the while loop
		remove(ball);
		remove(info);
		drawInfoLabel();
		endingMessages();
	}

	private void endingMessages() {
		// Either all bricks are broken or the number of turns have been used up. Display the appropriate message.
		if (brickCount == 0 ) {
			GLabel congratsMessage = new GLabel("Congratulations! You broke all the bricks!!", .25*getWidth(), getHeight()/2);
			congratsMessage.setFont(Font.SANS_SERIF);
			congratsMessage.setColor(Color.MAGENTA);
			add(congratsMessage);
		}
		else {
			GLabel gameOver = new GLabel("So sad :-( You didn't break all the bricks. GAME OVER", .25*getWidth(), getHeight()/2);
			gameOver.setFont(Font.SANS_SERIF);
			gameOver.setColor(Color.RED);
			add(gameOver);
		}
	}

	private GObject getCollidingObject() {
		// check to see if the ball is at the same location as either the paddle or a brick
		// look at all four corners of the ball object
		// either return the object or return null
		if  (getElementAt(ball.getX(), ball.getY() ) != null) {
			return getElementAt(ball.getX(), ball.getY() );
		}
		else if (getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS) != null) {
			return getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS);
		}
		else if (getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY()) != null) {
			return getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY());
		}
		else if (getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS) != null) {
			return getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS);
		}
		else return (null);

	}


	private void handleBallCollision() {
		// This method is called when the ball reaches an edge of the playing field or object
		// define the top edge of the paddle
		double paddleTop = paddle.getY();



		// hit the paddle: reverse direction
		if (collider == paddle) {
			//put the ball back outside the paddle boundary
			//First find out if the ball is hitting the paddle from above or below the paddle
			if (vy > 0)	{
				// ball heading downward
				vy = -vy;
				double diff = ball.getY() - (paddleTop - 2*BALL_RADIUS);
				ball.move( 0, -2 * diff);
			}

		}

		// hit a brick: remove the brick and reverse direction
		if ( (collider != null) && (collider != paddle) ){
			remove(collider);
			score +=1;
			remove(info);
			drawInfoLabel();
			brickCount -= 1;
			vy = -vy;
		}

		// left edge
		if ( (ball.getX() <= 0) ){
			vx = -vx;
			//put the ball back into the canvas
			double diff = ball.getX() - (0);
			ball.move( (-2 * diff), 0 );		
		}

		// right edge
		if ( ball.getX() >= (WIDTH - 2*BALL_RADIUS) ){
			vx = -vx;
			//put the ball back into the canvas
			double diff = ball.getX() - (WIDTH - 2*BALL_RADIUS);
			ball.move((-2 * diff), 0 );		
		}

		// top edge
		if (ball.getY() <= 0) {
			vy = -vy;
			//put the ball back into the canvas
			double diff = ball.getY() - (0);
			ball.move(0, -2 * diff);
		}

		// bottom edge
		if (ball.getY() >= (HEIGHT - 2*BALL_RADIUS)) {
			// this commented section makes you lose when the ball hits the bottom edge
			remove(ball);
			turnsRemaining -= 1;
			remove(info);
			drawInfoLabel();
			pause(DELAY*50);
			createBall();
			initializeBallVelocity();
			//end  you lose when the ball hits the bottom edge
			// */ 

			/* this section makes the ball bounce off the bottom edge for development purposes
			vy = -vy;
			//put the ball back into the canvas
			double diff = ball.getY() - (HEIGHT - 2*BALL_RADIUS);
			ball.move(0, -2 * diff);
			// end of this section makes the ball bounce off the bottom edge for development purposes
			 */
		}
	}

	private void moveBall() {
		// moves the ball in a straight line until it hits an edge or object
		//println("in moveBall");

		while ( (collider == null) && (ball.getX() >= 0) &&  ( ball.getX() <= (WIDTH - 2*BALL_RADIUS) ) && (ball.getY() >= 0) &&  ( ball.getY() <= (HEIGHT - 2*BALL_RADIUS) )) {
			ball.move(vx, vy);
			// keep checking for an object collision
			collider = getCollidingObject();
			pause(DELAY);
		}
	}

	private void initializeBallVelocity() {
		// start with 3.0 downward and randomize the y velocity
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		// Randomize forward and backward as well as speed 
		if (rgen.nextBoolean(.5)) vx = -vx;
	}

	private void createBall() {
		// make the filled circle ball and place it in the center
		ball = new GOval( (WIDTH/2 - BALL_RADIUS), (HEIGHT/2 - BALL_RADIUS), BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add (ball);
	}

	//*****************************************************************************************************	
	// Instance variables
	private int turnsRemaining;
	private GRect paddle;
	private GRect brick;
	private ArrayList<GRect> brickList = new ArrayList<GRect>();

	private int brickCount;
	private int score;
	private GLabel info;
	// velocities of the bouncing ball
	private double vx, vy;
	private GOval ball;
	// get ready to generate a random velocity for the ball
	private RandomGenerator rgen = RandomGenerator.getInstance();
	// collider is the object that the ball has overlapped 
	private GObject collider;
}
