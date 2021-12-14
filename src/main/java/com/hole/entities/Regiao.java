package com.hole.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "regiao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Regiao {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String sigla;

  private String nome;

  @OneToMany
  @JoinColumn(name = "regiao_id")
  private List<Estado> estados;

  public Regiao(Long id) {
    this.id = id;
  }
}
