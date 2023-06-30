package pojo;

import java.util.ArrayList;

public record Pedido(int rand, ArrayList<Carrito> carritoArrayList,Cliente cliente) {
}
