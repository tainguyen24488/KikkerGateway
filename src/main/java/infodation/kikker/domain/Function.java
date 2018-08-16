package infodation.kikker.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Function.
 */
@Entity
@Table(name = "kikker_function")
public class Function implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "actived")
    private Boolean actived;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Function name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActived() {
        return actived;
    }

    public Function actived(Boolean actived) {
        this.actived = actived;
        return this;
    }

    public void setActived(Boolean actived) {
        this.actived = actived;
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
        Function Function = (Function) o;
        if (Function.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), Function.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Function{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", actived='" + isActived() + "'" +
            "}";
    }
}
