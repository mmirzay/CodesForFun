package structural.facade.facade;

import structural.facade.complexsystem.RobotBody;
import structural.facade.complexsystem.RobotColor;
import structural.facade.complexsystem.RobotMetal;

public class RobotFacade {
	private RobotBody body;
	private RobotColor color;
	private RobotMetal metal;

	public void constructRobot(String color, String metal) {
		this.body = new RobotBody();
		this.color = new RobotColor(color);
		this.metal = new RobotMetal(metal);
	}

	public RobotBody getBody() {
		return body;
	}

	public RobotColor getColor() {
		return color;
	}

	public RobotMetal getMetal() {
		return metal;
	}
}
