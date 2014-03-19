//Ammar Tariq Khan
//Student no: 2824199
// HCI and GUI

/* Main.java
 *
 * this is a simple program that will implement the game of reversi, it will only deal with the game itself and a
 * simple scoreboard.
 */

/** includes **/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** classes **/

public class Main extends JFrame {

	/** constructors **/
	public Main() {
		setSize(655,675);			// setting the size of the frame
		setTitle("Reversi");		// setting the name of the frame
		setDefaultCloseOperation(EXIT_ON_CLOSE);		// setting the default clos operation
		widget =new ReversiWidget();		// Ititializing new widget
		getContentPane().add(widget);		// adding it to the panel

	}

	/** public functions **/
	public static void main(String[] args) {
		Main r= new Main();		// Initializing the main function
		r.setVisible(true);		// setting the frame visible


	}

	/** private fields **/
	ReversiWidget widget;	// where the game is being played
}

class ReversiWidget extends JComponent implements MouseListener {

	/** constructors **/
	public ReversiWidget() {
		cyan = new Color(0,255,255);		// setting the color
		white= new Color(255,255,255);		// Setting white Color
		black =new Color(0,0,0);			//Setting Black Color
		initialState();						// Starting the game/calling the Initial state function
		this.addMouseListener(this);		// Calling the mouse listener

	}

	/** public functions **/

	// method required by MouseListener. not used
	public void mouseClicked(MouseEvent event) {

	}

	// method required by MouseListener. not used
	public void mouseEntered(MouseEvent event) {

	}

	// method required by MouseListener. not used
	public void mouseExited(MouseEvent event) {

	}

	// will react to mouse press events on the widget
	public void mousePressed(MouseEvent event) {
		int posx=this.getWidth()/8;				// this gives the width of one cell
		int x=event.getX();						// It gives the index value of x
		int posy=this.getHeight()/8;			// this gives the Height of one cell
		int y=event.getY();						// It gives the index value of y
		oldx=x/posx;							// Storing the cell number
		oldy=y/posy;							// Storing the cell number
	}

	// will react to mouse release events on the widget
	public void mouseReleased(MouseEvent event) {
		int posx=this.getWidth()/8;				// this gives the width of one cell
		int x=event.getX();						// It gives the index value of x
		int posy=this.getHeight()/8;			// this gives the Height of one cell
		int y=event.getY();						// It gives the index value of y
		int newx=x/posx;						// calculating the new position of x
		int newy=y/posy;						// calculating the new position of y
		if(newx==oldx&&oldy==newy){				// checking if the user clicked in the same box

		attemptMove(newx,newy,current_player);		// calling the function attempt move


		}
		else ;
	}

	// repaints the widget when an update of any kind is made
	public void paintComponent(Graphics g) {
		Graphics2D g1=(Graphics2D)g;		//typcasting graphics variable into Graphics2D

		g1.setColor(cyan);					// setting the color as cyan
		g1.fillRect(0,0,this.getWidth(),this.getHeight());		// this will color the rectangles
		drawGrid(g1);							// calls function to draw the lines
		drawPieces(g1);							// calls the Function to draw the pieces

	}

	/** private functions **/

	// will take in a position (x,y) a player and will attempt to make a move. if successful then it will place the
	// piece and update the game board.
	private void attemptMove(int x, int y, int player) {
		boolean found=false;


		int opp=1;
		if(player== 1)			// checking the value of palyer
			opp=2;				// checking the value of palyer
		else if(player==2)		// checking the value of palyer
			opp=1;				// checking the value of palyer
		if (inPlay==true)				//Checking if the game is in Play
		{
			if(checkPiece(x,y)==0){				// checking if the piece if empty
						if(x>0&&y>0&&x<7&&y<7){			// Condition to check for the box excluding corner boxes first row first coloum last row las coloum
						// checking the adjacent 8 boxes for opponent
						if(board[x-1][y-1]==opp||board[x][y-1]==opp||board[x+1][y-1]==opp||board[x+1][y]==opp||board[x+1][y+1]==opp||board[x][y+1]==opp||board[x-1][y+1]==opp||board[x-1][y]==opp){
							found=true;		// setting the value true if found
						}

						}
						else if(x==0&&y==0)		// Checking the corner box 0,0
						{
								if(board[x][y+1]==opp||board[x+1][y]==opp||board[x+1][y+1]==opp){// checking adjacent 3 boxes

							found=true;		// setting the value true if found

						}

						}
							else if(x==0&&y==7)		// Checking the corner box 0,7
						{
								if(board[x][y-1]==opp||board[x+1][y-1]==opp||board[x+1][y]==opp){	// checking adjacent 3 boxes

							found=true;			// setting the value true if found

						}


						}
						else if(x==7&&y==7)		// Checking the corner box 7,7
						{
								if(board[x-1][y-1]==opp||board[x-1][y]==opp||board[x][y-1]==opp){		// checking adjacent 3 boxes

							found=true;				// setting the value true if found


						}


						}
							else if(x==7&&y==0)			// Checking the corner box 7,0
						{
								if(board[x-1][y]==opp||board[x-1][y+1]==opp||board[x][y+1]==opp){	// checking adjacent 3 boxes

							found=true;					// setting the value true if found

						}

						}
						else if(x==0&&y>0&&y<7)				// checking for the boxes where index x=0
						{
								if(board[x][y-1]==opp||board[x+1][y-1]==opp||board[x+1][y]==opp||board[x+1][y]==opp||board[x+1][y+1]==opp){		// checking adjacent 5 boxes

							found=true;					// setting the value true if found

						}


						}
						else if(x==7&&y>0&&y<7)			// checking for the boxes where index x=7
						{
								if(board[x][y-1]==opp||board[x-1][y-1]==opp||board[x-1][y]==opp||board[x-1][y+1]==opp||board[x][y+1]==opp){			// checking adjacent 5 boxes

							found=true;					// setting the value true if found

						}

						}
						else if(y==7&&x>0&&x<7)			// checking for the boxes where index y=7
						{
								if(board[x-1][y]==opp||board[x-1][y-1]==opp||board[x][y-1]==opp||board[x+1][y-1]==opp||board[x+1][y]==opp){		// checking adjacent 5 boxes

							found=true;			// setting the value true if found


						}

						}
						else if(y==0&&x>0&&x<7)		// checking for the boxes where index y=0
						{
								if(board[x-1][y]==opp||board[x-1][y+1]==opp||board[x][y+1]==opp||board[x+1][y]==opp||board[x+1][y+1]==opp){		// checking adjacent 5 boxes

							found=true;		// setting the value true if found
						}
						}
						if(found == true && reverseChainAvailable(x,y,player)==true)
						{
							board[x][y]=player;					// setting the value as the player 1 value

							repaint();										// this will repaint the widget
							swapPlayers();								//function call to swap the players
							System.out.println(determineEndGame());		//it calls a function to determing if the end game is reached.
							updatePlayerScores();						// it calls a function to update player score

					}

				else return;		// return if it doesnot satisfy any the condition
		}
		else return;				// return if it doesnot satisfy any the condition

	}
	}

	// checks if there is a piece in a given position. returns 0 if empty, -1 if out of bounds, 1 for player 1, and
	// 2 for player 2
	private int checkPiece(int x, int y) {
		if (board[x][y]==0)			// Check piece equals to 0
			return 0;					// return the integer 0 for empty
			else if(board[x][y]==1)			// Check piece equals to 1
			return 1;					// return the integer 1 for player 1
			else if(board[x][y]==2)			// Check piece equals to 2
			return 2;						// return the integer 2 for player 2
			else
		return -1;						// return -1 if out of bound
	}

	// determines if a valid reverse chain can be made from the position (x, y) in the given direction (dx, dy) and a
	// given player
	private boolean determineChain(int x, int y, int dx, int dy, int player) {
		boolean oppplayer=false;			// Setting Boolean variable opposition player as false
		boolean ownplayer=false;	// Setting Boolean variable own player as false
		int a=0;

		int x1=x+dx;				// initializing a variable
		int y1=y+dy;				// initializing a variable
		int player2=0;				// initializing a variable
		if(player==1)				// check if player is equal to player1
			player2=2;				// set player2 as 2
		else
			player2=1;				//else set player1 as 1

		while (x1>0&&x1<7&&y1>0&&y1<7&& ownplayer==false){			// check if it is not our of bound and own player is equla to false
			if(board[x1][y1]!=0){				// Check if the value at x1,y1 is not equal to zero.
			if(board[x1][y1]==player2)			// Check if the value at x1,y1 is not equal to 2
			{
				a++;						// increment the counter
			}
			else if(board[x1][y1]==player)		// Check if the value at x1,y1 is not equal to 2
			{
				ownplayer=true;					// set ownplayer as true

			}
			}
			x1=x1+dx;						// Add dx in x1
			y1=y1+dy;						// Add dy in y1
		}


		if (ownplayer==true){				// Check if own player is true

			return true;					// return true if own player is true
		}
			else
		return false;						// return false if not true
	}

	// determines if an end game state has been reached. this will happen if there are zero spaces on the board, if one
	// player has lost all of their pieces, or there are no valid moves left for either player
	private boolean determineEndGame() {
		boolean found= false;				// Settign a boolean variable as false
		for(int i=0;i<board.length;i++){		// Traversing through the Multidimensional Array
			for(int j=0;j<board.length;j++){
				if (board[i][j]==0)				// Checking if the value at every index is equal to 0
				{ found= true;					// Setting found as true
				}
			}
		}
		if(found!=true){					// check if the boolean variable is not true
			JOptionPane.showMessageDialog(null,"Game Over");		// Shows a message Game over as there are no more moves
			return true;
		}
	/*	for(int i=0;i<board.length;i++)
		{
			for (int i=0;i<board.length;i++)
			{
				if(board[i][j])
			}
		}*/
		else if(player_1_score==0)			// check if the player 1 score is 0
		{
			JOptionPane.showMessageDialog(null,"player 1 has lost all its pieces");			// displays a message the player 1 has lost
			return true;
		}
		else if(player_2_score==0){				// Check if the player 2 score is 0
			JOptionPane.showMessageDialog(null,"player 2 has lost all its pieces");			// Displays a message that player 3 has lost
			return true;
		}

		else
		return false;
	}

	// will draw the grid for the game. this assumes a 640 by 640 grid
	private void drawGrid(Graphics2D g2d) {
		g2d.setColor(black);							// Setting the color of lines
		g2d.drawLine(0,0,this.getWidth(),0);			// drawing outer lines
		g2d.drawLine(0,this.getWidth(),0,0);
		g2d.drawLine(this.getWidth()-1,0,this.getWidth()-1,this.getWidth()-1);
		g2d.drawLine(this.getWidth()-1,this.getHeight()-1,0,this.getHeight()-1);
		int posx= this.getWidth()/8;
		int posy= this.getHeight()/8;
		for (int i=0;i<8;i++)		// drawing horizontal lines
		{
			g2d.drawLine(posx*i,0,posx*i,this.getHeight());
		}
		for(int k=0; k<8; k++)		// drawing vertical Lines
		{
			g2d.drawLine(0,posy*k,this.getWidth(),posy*k);
		}

	}

	// will draw the pieces that are currently on the board. assumes a widget size of 640 square
	private void drawPieces(Graphics2D g2d) {
		int posx= this.getWidth()/8;			// this gives positin of the cell in x-axis
		int posy= this.getHeight()/8;			// this gives positin of the cell in y-axis

		for(int j=0;j<board.length;j++)				// This will iterate through the multi dimensional array
		{
			for (int k=0;k<board.length;k++)
			{
				if(board[j][k]==2)					// if the value board[j][k] is 2 it will make a black oval
				{
					g2d.setColor(black);				// Set color as black
					g2d.fillOval(posx*j,posy*k,posx,posy);		// fill oval with white Black
				}

			else if(board[j][k]==1)					// if the value board[j][k] is 2 it will make a White oval
				{
					g2d.setColor(white);			// set the color as white
					g2d.fillOval(posx*j,posy*k,posx,posy);	// fill oval with white Color
				}
		}
		}
	}

	// will initialise the game board to the starting state
	private void initialState() {
		board = new int[8][8];			// Initializing the multi dimentional array
		for (int j=0;j<8;j++)
		{
			for(int k=0;k<8;k++)		// Initializing All values to 0
			{
				board[j][k]=0;
			}
		}
		board[3][4]=2;					// Setting the values of start of the game
		board[3][3]=1;
		board[4][3]=2;
		board[4][4]=1;
		player_1_score=2;				// Initial Score of the player 1 is 2
		player_2_score=2;				// Initial Score of the player 2 is 2
		current_player=1;				// Setting the player 1 at start of the game
		inPlay=true;					// Boolean vaalue set to true at the start of the game

	}

	// given a position (x, y) and a player this will determine if there is a valid move to be made at the given
	// position by checking for the availability of a reverse chain in any direction
	private boolean reverseChainAvailable(int x, int y, int player) {
		boolean found= false;
		int opp=1;
		if(player== 1)			// checking the value of palyer
			opp=2;				// checking the value of palyer
		else if(player==2)		// checking the value of palyer
			opp=1;

			if(board[x-1][y]==opp&&x-1>0){					// Checking for the opposition piece in adjacent cell
				found=determineChain(x,y,-1,0,player);			// call the function to determine chain method
				if(found==true)
					reversePieces(x,y,-1,0,player);				//Call the Revers Piece method

			}
			else if(board[x-1][y-1]==opp&&x-1>0&&y-1>0){		// Checking for the opposition piece in adjacent cell
				found=determineChain(x,y,-1,-1,player);			// call the function to determine chain method
				if(found==true)
					reversePieces(x,y,-1,-1,player);			//Call the Revers Piece method
			}
			else if(board[x][y-1]==opp&&y-1>0){				// Checking for the opposition piece in adjacent cell
				found=determineChain(x,y,0,-1,player);		// call the function to determine chain method
				if(found==true)
					reversePieces(x,y,0,-1,player);			//Call the Revers Piece method
			}
			else if(board[x+1][y-1]==opp&&x+1<7&&y-1<0){		// Checking for the opposition piece in adjacent cell
				found=determineChain(x,y,1,-1,player);			// call the function to determine chain method
				if(found==true)
					reversePieces(x,y,1,-1,player);			//Call the Revers Piece method

			}
			else if(board[x+1][y]==opp&&x+1<7){				// Checking for the opposition piece in adjacent cell
				found=determineChain(x,y,1,0,player);		// call the function to determine chain method
				if(found==true)
					reversePieces(x,y,1,0,player);				//Call the Revers Piece method
			}
			else if(board[x+1][y+1]==opp&&x+1<7&&y+1<7){		// Checking for the opposition piece in adjacent cell
				found=determineChain(x,y,1,1,player);			// call the function to determine chain method
				if(found==true)
					reversePieces(x,y,1,1,player);				//Call the Revers Piece method
			}
			else if(board[x][y+1]==opp&&y+1<7){				// Checking for the opposition piece in adjacent cell
				found=determineChain(x,y,0,1,player);		// Call the function to determine chain method
				if(found==true)
					reversePieces(x,y,0,1,player);			//Call the Revers Piece method
			}
			else if(board[x-1][y+1]==opp&&x-1>0&&y+1<7){		// Checking for the opposition piece in adjacent cell
				found=determineChain(x,y,-1,1,player);			// Call the function to determine chain method
				if(found==true)
					reversePieces(x,y,-1,1,player);				//Call the Revers Piece method
			}


		return found;
	}

	// given a position (x, y), direction (dx, dy) and a player this will reverse all opponents pieces in a given
	// direction. NOTE: this assumes that determineChain has been used first. method does not perform checks
	private void reversePieces(int x, int y, int dx, int dy, int player) {
			boolean oppplayer=false;		//
		boolean ownplayer=false;
		int a=0;
		int x1=x+dx;
		int y1=y+dy;
		int player2=0;
		if(player==1)
			player2=2;
		else
			player2=1;

		while (x1>0&&x1<7&&y1>0&&y1<7&& ownplayer==false&&board[x1][y1]!=0){

			if(board[x1][y1]==player2)
			{
				a++;

			}
			else if(board[x1][y1]==player)
			{
				ownplayer=true;

			}
			x1=x1+dx;
			y1=y1+dy;
		}
		 x1=x+dx;
		 y1=y+dy;
			while (x1>0&&x1<7&&y1>0&&y1<7&& ownplayer==true &&board[x1][y1]!=0){

			if(board[x1][y1]==player2)
			{
				board[x1][y1]=player;

			}
			else if(board[x1][y1]==player)
			{
				ownplayer=false;
			}
			x1=x1+dx;
		 	y1=y1+dy;

			}


	}

	// called at the end of a valid turn this will swap the current players
	private void swapPlayers() {
		if(current_player==1)			// Checking the value of current player
			current_player=2;			//Initializing the value to 2
		else
			current_player=1;			//Initializing the value to 1

	}

	// updates the player scores after a piece has been placed
	private void updatePlayerScores() {
		player_1_score=0;		// Resetting the score at player 1 0
		player_2_score=0;		//Resetting the score of player 2 at 0
		for(int i=0;i<board.length;i++)			//Traversing through the multi-dimensional Array
		{
			for(int j=0;j<board.length;j++)
			{
				if(board[i][j]==1)					//Checkking at every index if its valus is 1
					player_1_score++;				// Incrementing the Score of player 1 by 1
				else if(board[i][j]==2)				//Checkking at every index if its valus is 2
					player_2_score++;				// Incrementing the Score of player 2 by 1
				else ;								// if there is some other value ignore
			}
		}

	}

	/** private fields **/
	int board[][];						// the state of the game board
	int oldx, oldy;						// denotes where the player clicked when he pressed the mouse button
	int current_player;					// denotes who the current player is
	int player_1_score, player_2_score;	// denotes the score each player has in the game thus far
	boolean inPlay;						// indicates if the game is being played at the moment
	Color black, cyan, white;			// color objects that represent their named colours

}