package luminac.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class ExtraEffectsMenu extends GuiScreen {
	
	private final List<Particle> particles;
	private int mouseX, mouseY, count;
	
	public ExtraEffectsMenu(final int mouseY, final int mouseX) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.count = 500;
		this.particles = new ArrayList<Particle>();
		for (int count = 0; count<= this.count; ++count) {
			this.particles.add(new Particle(new Random().nextInt(mouseX), new Random().nextInt(mouseY)));
		}
	}
	
	public void drawParticles() {
		this.particles.forEach(particle -> particle.drawParticle());
	}
	
	public class Particle {
		
		private int xPos, yPos;
		
		public Particle(final int xPos, final int yPos) {
			this.xPos = xPos;
			this.yPos = yPos;
		}
		
		public void drawParticle() {
			this.yPos += new Random().nextInt(2);
			this.xPos += new Random().nextInt(2);
			final int particleSize = 2;
			
			if (this.xPos > ExtraEffectsMenu.this.mouseX) {
				this.xPos = -particleSize;
			}
			
			if (this.yPos > ExtraEffectsMenu.this.mouseY) {
				this.yPos = -particleSize;
			}
			
			Gui.drawRect(this.xPos, this.yPos, this.xPos + particleSize, this.yPos + particleSize, -1);
		}
	}
}