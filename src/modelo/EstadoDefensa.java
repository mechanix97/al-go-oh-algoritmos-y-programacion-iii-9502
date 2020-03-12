package modelo;

import excepciones.ImposibleAtacarEnEstadoDeDefensaException;

public class EstadoDefensa extends Estado {	
	
	@Override
	public boolean enPosicionDeAtaque() {
		return false;
	}

	@Override
	public void atacarA(CartaMonstruo monstruoAtacado, CartaMonstruo monstruoAtacante) {
		throw new ImposibleAtacarEnEstadoDeDefensaException();
	}

	@Override
	public void enfrentarA(CartaMonstruo monstruoAtacante, CartaMonstruo monstruoAtacado) {
		monstruoAtacado.recibirDanioAPuntosDeDefensa(monstruoAtacante.extraerPuntosAtaque());
	}
}
