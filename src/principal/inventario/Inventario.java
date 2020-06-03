package principal.inventario;

import java.util.ArrayList;
import principal.inventario.armas.Arma;
import principal.inventario.consumible.Consumible;

public class Inventario {

    public final ArrayList<Objeto> objetos;

    public Inventario() {
        this.objetos = new ArrayList();
    }

    public void recogerObjetos(final ContenedorObjetos co) {
        for (Objeto objeto : co.getObjetos()) {
            if (objetoExiste(objeto)) {
                aumentarCantidadObjeto(objeto, objeto.getCantidad());
            } else {
                objetos.add(objeto);
            }
        }
    }

    public void recogerObjetos(final ObjetoSuelto o) {
        if (objetoExiste(o.getObjeto())) {
            aumentarCantidadObjeto(o.getObjeto(), o.getObjeto().getCantidad());
        } else {
            objetos.add(o.getObjeto());
        }
    }

    public boolean aumentarCantidadObjeto(final Objeto objeto, final int cantidad) {
        boolean incrementado = false;

        for (Objeto objetoActual : objetos) {
            if (objetoActual.getId() == objeto.getId()) {
                objetoActual.aumentarCantidad(cantidad);
                incrementado = true;
                break;
            }
        }
        return incrementado;
    }

    public boolean objetoExiste(final Objeto objeto) {
        boolean existe = false;
        for (Objeto o : objetos) {
            if (o.getId() == objeto.getId()) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public ArrayList<Objeto> getObjetosConsumibles() {
        ArrayList<Objeto> consumibles = new ArrayList();
        for (Objeto objeto : objetos) {
            if (objeto instanceof Consumible) {
                consumibles.add(objeto);
            }
        }
        return consumibles;
    }

    public ArrayList<Objeto> getObjetosArmas() {
        ArrayList<Objeto> armas = new ArrayList();
        for (Objeto objeto : objetos) {
            if (objeto instanceof Arma) {
                armas.add(objeto);
            }
        }
        return armas;
    }

    public Objeto getObjeto(final int id) {
        for (Objeto objeto : objetos) {
            if (objeto.getId() == id) {
                return objeto;
            }
        }
        return null;
    }

}