import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MSLabel extends JLabel {

	private MSCell cell;
	private ArrayList<BombListener> listeners;


	public MSLabel() {
		listeners = new ArrayList<BombListener>();
		setPreferredSize(new Dimension(30,30));
		setIcon(new ImageIcon("MineSweeperIcons/Empty.png"));
		addMouseListener(new MSListener());
		cell = new MSCell();
	}

	public void setAsBomb(){
		cell.setAsBomb();
	}

	public boolean isBomb(){
		return cell.isBomb();
	}

	public void setBombsNear(int n){
		cell.setBombsNear(n);
	}

	public int getBombsNear(){
		return cell.getBombsNear();
	}

	public void addBombListener(BombListener b){
		listeners.add(b);
	}

	public void removeBombListener(BombListener b){
		listeners.remove(b);
	}

	public void notifyListeners(){
		for(BombListener b : listeners){
			b.update(new BombEvent(this));
		}
	}

	private void reveal(){
		if(isBomb())
			setIcon(new ImageIcon("MineSweeperIcons/Bomb.png"));
		else{
			setIcon(new ImageIcon("MineSweeperIcons/" + getBombsNear() + ".png"));
		}

		cell.reveal();
		notifyListeners();

		for(BombListener x : listeners){
			removeBombListener(x);
		}

		repaint();
	}

	private class MSListener implements MouseListener {

		boolean in = false;

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.isMetaDown()){
				if(cell.isHidden()){
					cell.toggleFlagged();

					if(cell.isFlagged())
						setIcon(new ImageIcon("MineSweeperIcons/Flag.png"));
					else
						setIcon(new ImageIcon("MineSweeperIcons/Empty.png"));
				}
				else{}
			}
			else{
				if(cell.isHidden())
					reveal();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
