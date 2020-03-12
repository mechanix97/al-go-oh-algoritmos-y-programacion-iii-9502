package modelo;

public class EstadoAtaque extends Estado {

	@Override
	public boolean enPosicionDeAtaque() {
		return true;
	}

	@Override
	public void atacarA(CartaMonstruo monstruoAtacado, CartaMonstruo monstruoAtacante) {
		monstruoAtacado.enfrentarA(monstruoAtacante);
	}

	@Override
	public void enfrentarA(CartaMonstruo monstruoAtacante, CartaMonstruo monstruoAtacado) {
		monstruoAtacado.recibirDanioAPuntosDeAtaque(monstruoAtacante.extraerPuntosAtaque());
		monstruoAtacante.recibirDanioAPuntosDeAtaque(monstruoAtacado.extraerPuntosAtaque());
	}
}
