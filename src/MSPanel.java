import javax.swing.*;
import java.awt.*;
import java.util.EventListener;
import java.util.Random;


public class MSPanel extends JPanel implements BombListener {
	private MSLabel[][] squares;
	private int rows, cols, numShowing;
	private double difficulty;

	public MSPanel(int rows, int cols, double difficulty) {
		squares = new MSLabel[rows][cols];
		setLayout(new GridLayout(rows, cols));
		this.rows = rows;
		this.cols = cols;
		this.difficulty = difficulty;

		for(int x = 0; x < rows; x++){
			for(int y = 0; y < cols; y++){
				squares[x][y] = new MSLabel();
				add(squares[x][y]);
			}
		}
		for(int x = 0; x < rows; x++){
			for(int y = 0; y < cols; y++){
				squares[x][y].addBombListener(this);
			}
		}

		setBombs();
		setNumbers();
		repaint();
	}

	private void setBombs(){
		int placed = 0;
		int r, c;

		while(placed < difficulty*rows*cols){
			r = (int)(Math.random()*rows);
			c = (int)(Math.random()*cols);
			while(getNum(r,c).isBomb()){
				r = (int)(Math.random()*rows);
				c = (int)(Math.random()*cols);
			}
			getNum(r,c).setAsBomb();
			placed++;
		}
	}

	private MSLabel getNum(int r, int col){
		return squares[r][col];
	}

	private void setNumbers(){

		for(int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				int bombsNear = 0;

				if((x-1) >= 0){
					if((y-1) >= 0){
						if(squares[x-1][y-1].isBomb())
							bombsNear++;
					}
					if((y+1) < cols){
						if(squares[x-1][y+1].isBomb())
							bombsNear++;
					}
					if(squares[x-1][y].isBomb())
						bombsNear++;
				}

				if((x+1) < rows){
					if((y-1) >= 0){
						if(squares[x+1][y-1].isBomb())
							bombsNear++;
					}
					if((y+1) < cols){
						if(squares[x+1][y+1].isBomb())
							bombsNear++;
					}
					if(squares[x+1][y].isBomb())
						bombsNear++;
				}

				if((y-1) >= 0){
					if(squares[x][y-1].isBomb())
						bombsNear++;
				}

				if((y+1) < cols){
					if(squares[x][y+1].isBomb())
						bombsNear++;
				}
				squares[x][y].setBombsNear(bombsNear);
			}
		}

	}

	public void update(BombEvent b){
		MSLabel source = (MSLabel)b.getSource();
		if(source.isBomb()){
			JOptionPane.showMessageDialog(null,"You Lose!");
			System.exit(0);
		}

		else{
			numShowing++;
			if(numShowing == difficulty*rows*cols){
				JOptionPane.showMessageDialog(null,"You Win! Congrats!");
				System.exit(0);
			}
		}
	}



}
