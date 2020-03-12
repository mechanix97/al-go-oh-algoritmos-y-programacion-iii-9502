package modelo;

public abstract class Estado {
			
	public abstract boolean enPosicionDeAtaque(); 

	public abstract void atacarA(CartaMonstruo monstruoAtacado, CartaMonstruo monstruoAtacante);

	public abstract void enfrentarA(CartaMonstruo monstruoAtacante, CartaMonstruo monstruoAtacado);
}
