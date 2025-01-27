
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Pacman extends Unit {
	private String pacman1S = "images/pacman.png";
	private String pacman2upS = "images/up1.png";
	private String pacman3upS = "images/up2.png";
	private String pacman4upS = "images/up3.png";
	private String pacman2downS = "images/down1.png";
	private String pacman3downS = "images/down2.png";
	private String pacman4downS = "images/down3.png";
	private String pacman2leftS = "images/left1.png";
	private String pacman3leftS = "images/left2.png";
	private String pacman4leftS = "images/left3.png";
	private String pacman2rightS = "images/right1.png";
	private String pacman3rightS = "images/right2.png";
	private String pacman4rightS = "images/right3.png";

	private final int pacAnimationDelay = 2;
	private final int pacmanAnimationCount = 4;

	private int pacAnimationCount = pacAnimationDelay;
	private int pacAnimationDir = 1;
	private int pacmanAnimationPos = 0;

	private int requestDirectionX;
	private int requestDirectionY;
	private int drawDirectionX;
	private int drawDirectionY;

	private Timer timer;

	private boolean alive;

	public Pacman() {
		super.setImages(pacman1S, pacman2upS, pacman3upS, pacman4upS,
				pacman2downS, pacman3downS, pacman4downS, pacman2leftS,
				pacman3leftS, pacman4leftS, pacman2rightS, pacman3rightS,
				pacman4rightS);
		speed = 6;
		alive = true;
	}

	public int getDrawDirectionX() {
		return drawDirectionX;
	}

	public void setDrawDirectionX(int drawDirectionX) {
		this.drawDirectionX = drawDirectionX;
	}

	public int getDrawDirectionY() {
		return drawDirectionY;
	}

	public void setDrawDirectionY(int drawDirectionY) {
		this.drawDirectionY = drawDirectionY;
	}

	public int getRequestDirectionX() {
		return requestDirectionX;
	}

	public void setRequestDirectionX(int requestDirectionX) {
		this.requestDirectionX = requestDirectionX;
	}

	public int getRequestDirectionY() {
		return requestDirectionY;
	}

	public void setRequestDirectionY(int requestDirectionY) {
		this.requestDirectionY = requestDirectionY;
	}

	public void doAnim() {

		pacAnimationCount--;

		if (pacAnimationCount <= 0) {
			pacAnimationCount = pacAnimationDelay; // make pacman to open his
													// mouth slowly
			pacmanAnimationPos += pacAnimationDir;

			if (pacmanAnimationPos == (pacmanAnimationCount - 1)
					|| pacmanAnimationPos == 0) {
				pacAnimationDir = -pacAnimationDir;
			}
		}
	}

	public void movePacman(int blockSize, MapSpot[][] levelContent,
			Score score, final TimeForKill timeForKill) {

		int positionX;
		int positionY;
		MapSpot pointOfMap;

		if (requestDirectionX == -directionX
				&& requestDirectionY == -directionY) {
			directionX = requestDirectionX;
			directionY = requestDirectionY;
			drawDirectionX = directionX;
			drawDirectionY = directionY;
		}

		if (columnPosition % blockSize == 0 && rowPosition % blockSize == 0) {
			positionX = columnPosition / blockSize;
			positionY = rowPosition / blockSize;
			pointOfMap = levelContent[positionY][positionX];

			if (pointOfMap.hasDot()) {
				pointOfMap.setDot(false);
				PacDot pacDot = new PacDot();
				score.addToScore(pacDot.getScore());
			}

			if (pointOfMap.hasPowerDot()) {
				pointOfMap.setPowerDot(false);
				PowerDot powerDot = new PowerDot();
				score.addToScore(powerDot.getScore());
				timeForKill.setKillerPacman(true);
				if (timeForKill.getRemainingTimeForKill() == 0) {
					timer(timeForKill);
				}
				timeForKill.setRemainingTimeForKill(timeForKill
						.getTimeForKill() / 1000);
			}

			if (requestDirectionX != 0 || requestDirectionY != 0) {
				if (!((requestDirectionX == -1 && requestDirectionY == 0 && pointOfMap
						.hasLeftWall())
						|| (requestDirectionX == 1 && requestDirectionY == 0 && pointOfMap
								.hasRightWall())
						|| (requestDirectionX == 0 && requestDirectionY == -1 && pointOfMap
								.hasTopWall()) || (requestDirectionX == 0
						&& requestDirectionY == 1 && pointOfMap.hasBottomWall()))) {
					directionX = requestDirectionX;
					directionY = requestDirectionY;
					drawDirectionX = directionX;
					drawDirectionY = directionY;
				}
			}

			// Check for standstill
			if ((directionX == -1 && directionY == 0 && pointOfMap
					.hasLeftWall())
					|| (directionX == 1 && directionY == 0 && pointOfMap
							.hasRightWall())
					|| (directionX == 0 && directionY == -1 && pointOfMap
							.hasTopWall())
					|| (directionX == 0 && directionY == 1 && pointOfMap
							.hasBottomWall())) {
				directionX = 0;
				directionY = 0;
			}
		}
		columnPosition = columnPosition + speed * directionX;
		rowPosition = rowPosition + speed * directionY;
	}

	public int getPacmanAnimationPos() {
		return pacmanAnimationPos;
	}

	public void timer(final TimeForKill timeForKill) {
		int delay = 1000;
		timer = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timeForKill.getRemainingTimeForKill() < 1) {
					timeForKill.setKillerPacman(false);
					timer.stop();
				}
				timeForKill.loweringRemainingTimeForKill();
			}
		});
		timer.start();
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
