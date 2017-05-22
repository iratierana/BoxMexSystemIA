package General;

public class BuscarCaminoPredeterminado {

	int espacio [][];
	Punto origen, destino;
	
	public void caminoPredeterminado(){
		espacio[1][1] = 8;
		espacio[1][2] = 8;
		espacio[1][3] = 8;
		espacio[2][3] = 8;
		//zeozer isteko lelengo estanterixan?
		espacio[3][3] = 8;
		espacio[4][3] = 8;
		//zeoze isteko 4.estanterixan?
		espacio[5][3] = 8;
		espacio[6][3] = 8;
		//zeoze isteko 7.estanterixan?
		espacio[7][3] = 8;
		espacio[7][4] = 8;
		espacio[7][5] = 8;
		espacio[6][5] = 8;
		//zeoze isteko 8.estanerixan?
		espacio[5][5] = 8;
		espacio[4][5] = 8;
		//zeoze isteko 5.estanterixan?
		espacio[3][5] = 8;
		espacio[2][5] = 8;
		//zeoze isteko 2.estanterixan?
		espacio[1][5] = 8;
		espacio[1][6] = 8;
		espacio[1][7] = 8;
		espacio[2][7] = 8;
		//zeoze isteko 3.estanterixan?
		espacio[3][7] = 8;
		espacio[4][7] = 8;
		//zepze isteko 6.estanterixan?
		espacio[5][7] = 8;
		espacio[6][7] = 8;
		//zeze isteko 9.estanterixan?
	}
}
