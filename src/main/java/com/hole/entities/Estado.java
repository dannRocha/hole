package com.hole.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estado {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String uf;

  private String nome;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "regiao_id", referencedColumnName = "id")
  @JsonIgnore
  private Regiao regiao;

  @OneToMany(mappedBy = "estado", cascade = CascadeType.REMOVE)
  private List<Cidade> cidades;

  public Estado(Long id) {
    this.id = id;
  }
}
