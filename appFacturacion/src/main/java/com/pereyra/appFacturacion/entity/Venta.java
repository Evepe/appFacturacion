package com.pereyra.appFacturacion.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

    /**
     * Representa un venta en el sistema
     */

    @Entity
    @Data
    @Table(name = "ventas")
    public class Venta  {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Schema(description = "ID de venta", example = "1")
        private Long idVenta;

        @Column(name = "fecha_creacion")
        @NotNull
        @Schema(description = "Fecha en que se efectua la venta", example = "2024-03-01")
        private String fechaHoracreacion;


        @ManyToOne
        @JoinColumn(name = "id_cliente")
        @Schema(description = "Cliente que realiza la compra", example = "Maria")
        private Cliente cliente;


        @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
        @Schema(hidden = true)
        private List<VentaDetalle> ventaDetalles;


        @Column(name = "total_venta")
        @Schema(name="Precio final de cada venta", example = "340.50")
        private double totalVenta;

        @Getter
        @Column(name = "completa")
        @Schema(hidden = true)
        private boolean completa;

        public Venta() {
            this.ventaDetalles = new ArrayList<>();
        }


        public Venta(Long idVenta, String fechaHoracreacion, Cliente cliente, List<VentaDetalle> ventaDetalles, double totalVenta, boolean completa) {
            this.idVenta = idVenta;
            this.fechaHoracreacion = fechaHoracreacion;
            this.cliente = cliente;
            this.ventaDetalles = ventaDetalles;
            this.totalVenta = totalVenta;
            this.completa = completa;
        }

        public void marcarCompleta() {
            this.completa = true;
        }



    }


