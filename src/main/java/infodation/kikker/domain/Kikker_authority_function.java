package infodation.kikker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Kikker_authority_function.
 */
@Entity
@Table(name = "kikker_authority_function")
public class Kikker_authority_function implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "authority_id", nullable = false)
    private Long authority_id;

    @NotNull
    @Column(name = "function_id", nullable = false)
    private Integer function_id;

    @OneToMany(mappedBy = "fk1")
    private Set<Kikker_function> fk1S = new HashSet<>();

    @OneToMany(mappedBy = "fk2")
    private Set<Jhi_authority> fk2S = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthority_id() {
        return authority_id;
    }

    public Kikker_authority_function authority_id(Long authority_id) {
        this.authority_id = authority_id;
        return this;
    }

    public void setAuthority_id(Long authority_id) {
        this.authority_id = authority_id;
    }

    public Integer getFunction_id() {
        return function_id;
    }

    public Kikker_authority_function function_id(Integer function_id) {
        this.function_id = function_id;
        return this;
    }

    public void setFunction_id(Integer function_id) {
        this.function_id = function_id;
    }

    public Set<Kikker_function> getFk1S() {
        return fk1S;
    }

    public Kikker_authority_function fk1S(Set<Kikker_function> kikker_functions) {
        this.fk1S = kikker_functions;
        return this;
    }

    public Kikker_authority_function addFk1(Kikker_function kikker_function) {
        this.fk1S.add(kikker_function);
        kikker_function.setFk1(this);
        return this;
    }

    public Kikker_authority_function removeFk1(Kikker_function kikker_function) {
        this.fk1S.remove(kikker_function);
        kikker_function.setFk1(null);
        return this;
    }

    public void setFk1S(Set<Kikker_function> kikker_functions) {
        this.fk1S = kikker_functions;
    }

    public Set<Jhi_authority> getFk2S() {
        return fk2S;
    }

    public Kikker_authority_function fk2S(Set<Jhi_authority> jhi_authorities) {
        this.fk2S = jhi_authorities;
        return this;
    }

    public Kikker_authority_function addFk2(Jhi_authority jhi_authority) {
        this.fk2S.add(jhi_authority);
        jhi_authority.setFk2(this);
        return this;
    }

    public Kikker_authority_function removeFk2(Jhi_authority jhi_authority) {
        this.fk2S.remove(jhi_authority);
        jhi_authority.setFk2(null);
        return this;
    }

    public void setFk2S(Set<Jhi_authority> jhi_authorities) {
        this.fk2S = jhi_authorities;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Kikker_authority_function kikker_authority_function = (Kikker_authority_function) o;
        if (kikker_authority_function.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kikker_authority_function.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Kikker_authority_function{" +
            "id=" + getId() +
            ", authority_id=" + getAuthority_id() +
            ", function_id=" + getFunction_id() +
            "}";
    }
}
