package com.principal.demo.services;

import com.principal.demo.model.Pedido;
import com.principal.demo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final Set<String> codigosGenerados = new HashSet<>();
    private final Random random = new Random();

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void guardarPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> findByUsuario_Id(int usuarioId) {
        return pedidoRepository.findByUsuario_Id(usuarioId);
    }

    @Override
    public String generarCodigoUnico() {
        int intentos = 0;
        int MAX_INTENTOS = 1000;

        while (intentos < MAX_INTENTOS) {
            String codigo = generarCodigoAleatorio();

            if (!codigosGenerados.contains(codigo)) {
                codigosGenerados.add(codigo);
                return codigo;
            }

            intentos++;
        }

        throw new RuntimeException("No se pudo generar un código único tras varios intentos");
    }

    public List<String> obtenerTodosLosCodigos() {
        return new ArrayList<>(codigosGenerados);
    }

    private String generarCodigoAleatorio() {
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            codigo.append(LETRAS.charAt(random.nextInt(LETRAS.length())));
        }

        for (int i = 0; i < 3; i++) {
            codigo.append(random.nextInt(10));
        }

        return codigo.toString();
    }
}
