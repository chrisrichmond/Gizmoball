package Model;

import physics.Vect;

public class CollisionDetails {
	private Vect velocity;
	private double tuc;
	public CollisionDetails(Vect velocity,double tuc){
		this.velocity=velocity;
		this.tuc=tuc;
	}
	public Vect getVelocity() {
		return velocity;
	}
	public void setVelocity(Vect velocity) {
		this.velocity = velocity;
	}
	public double getTuc() {
		return tuc;
	}
	public void setTuc(double tuc) {
		this.tuc = tuc;
	}

}
