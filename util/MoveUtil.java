package luminac.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public enum MoveUtil {

	instance;

	Minecraft mc = Minecraft.getMinecraft();

	public double getPosForSetPosX(double value) {
		double yaw = Math.toRadians(this.mc.thePlayer.rotationYaw);
		double x = -Math.sin(yaw) * value;
		return x;
	}

	public double getPosForSetPosZ(double value) {
		double yaw = Math.toRadians(this.mc.thePlayer.rotationYaw);
		double z = Math.cos(yaw) * value;
		return z;
	}

	public double getPosForSetPosXCustom(double value, float yawAdd) {
		double yaw = Math.toRadians(this.mc.thePlayer.rotationYaw + yawAdd);
		double x = -Math.sin(yaw) * value;
		return x;
	}

	public double getPosForSetPosZCustom(double value, float yawAdd) {
		double yaw = Math.toRadians(this.mc.thePlayer.rotationYaw + yawAdd);
		double z = Math.cos(yaw) * value;
		return z;
	}

	/* Credits to superblaubeere27 */
	public float getDirection() {
		float yaw = this.mc.thePlayer.rotationYaw;
		if (this.mc.thePlayer.moveForward < 0.0F) {
			yaw += 180.0F;
		}

		float forward = 1.0F;
		if (this.mc.thePlayer.moveForward < 0.0F) {
			forward = -0.5F;
		} else if (this.mc.thePlayer.moveForward > 0.0F) {
			forward = 0.5F;
		}

		if (this.mc.thePlayer.moveStrafing > 0.0F) {
			yaw -= 90.0F * forward;
		}

		if (this.mc.thePlayer.moveStrafing < 0.0F) {
			yaw += 90.0F * forward;
		}

		yaw *= 0.017453292F;
		return yaw;
	}

	public void setMotionSpeed(double value) {
		double motionX = getPosForSetPosX(value);
		double motionZ = this.getPosForSetPosZ(value);
		this.mc.thePlayer.motionX = motionX;
		this.mc.thePlayer.motionZ = motionZ;
	}

	public void setPosition(double value, double addY) {
		double posX = this.getPosForSetPosX(value);
		double posZ = this.getPosForSetPosZ(value);
		this.mc.thePlayer.setPosition(this.mc.thePlayer.posX + posX, this.mc.thePlayer.posY + addY,
				this.mc.thePlayer.posZ + posZ);
	}

}