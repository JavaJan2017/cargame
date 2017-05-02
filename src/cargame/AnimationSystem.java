package cargame;

import java.util.*;

public class AnimationSystem implements Runnable {
	protected GameSurface game;

	public AnimationSystem(GameSurface gameSurface) {
		game = gameSurface;
	}

	public void run() {
		long time = System.currentTimeMillis();
		for (;;) {
			ArrayList vehicles = game.getVehicles();
			// Update position, velocity etc. of vehicles
			long t = System.currentTimeMillis();
			long dt = t - time;
			float secs = (float) dt / 1000.0f; // Convert to seconds
			for (int i = 0; i < vehicles.size(); i++) {
				Vehicle v = (Vehicle) vehicles.get(i);
				v.update(secs);
			}
			// Check for collisions
			for (int i = 0; i < vehicles.size(); i++) {
				for (int j = i + 1; j < vehicles.size(); j++) {
					Vehicle vi = (Vehicle) vehicles.get(i);
					Vehicle vj = (Vehicle) vehicles.get(j);
					if (vi.intersects(vj)) {
						// Collision detected!
						// For now, simply reset the positions of the vehicles
						vi.updatePosition(50, 50);
						vj.updatePosition(450, 450);
					}
				}
			}
			time = System.currentTimeMillis();
			game.repaint();
			// Sleep for a short amount of time to allow the system to catch up.
			// This improves framerate substantially and avoids hiccups
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}
	}
}