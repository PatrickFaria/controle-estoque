package com.estoque.controle.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	@Id
	@EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(example = "1", required = true)
	private Long id;
	
    @NotNull	
    @Column(nullable = false)
    @ApiModelProperty(value = "Nome do Produto",example = "Roupa", required = true)
	private String nome;
	
    @Column(nullable = false, unique = true)
    @ApiModelProperty(value = "Código do Produto",example = "534262", required = true)
    private Long codigo;
    
    @NotNull
    @DecimalMin(value = "1")
    @Column(nullable = false)
    @ApiModelProperty(value = "Quantidade de Itens do Produto",example = "10", required = true)
	private Integer itens;
	
    @NotNull	
    @DecimalMin(value = "1")
    @Column(nullable = false)
    @ApiModelProperty(value = "Valor unitário do produto",example = "100.00", required = true)
	private BigDecimal valor;

}
