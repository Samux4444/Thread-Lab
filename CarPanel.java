import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
*/
public class CarPanel extends JComponent
{  
	private Car car1;
	private int x,y, delay;
	private CarQueue carQueue;
	private int direction;
	
	CarPanel(int x1, int y1, int d, CarQueue queue)
	{
		delay = d;
        x=x1;
        y=y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
	}
	public void startAnimation() {
	    class AnimationRunnable implements Runnable {
	        public void run() {
	            try {
	                while (true) {
	                    direction = carQueue.deleteQueue();
	                    if (direction == 0) { y -= 10; } // Up
	                    else if (direction == 1) { y += 10; } // Down
	                    else if (direction == 2) { x += 10; } // Right
	                    else if (direction == 3) { x -= 10; } // Left
	                    // Add boundary checking logic here
	                    repaint();
	                    Thread.sleep(delay * 1000);
	                }
	            } catch (InterruptedException exception) {
	                // Handle exception
	            }
	        }
	    }
	    new Thread(new AnimationRunnable()).start();
	}

	
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;

      car1.draw(g2,x,y);    
   }
}
