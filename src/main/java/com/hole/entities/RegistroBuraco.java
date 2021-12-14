package com.hole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registro_buraco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroBuraco {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String descricao;

  private String latitude;
  
  private String logitude;

  @ManyToOne
  @JoinColumn(name = "cidade_id", referencedColumnName = "id")
  private Cidade cidade;

}
