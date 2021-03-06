package infodation.kikker.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Kikker_organization.
 */
@Entity
@Table(name = "kikker_reseller")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "contact_zipcode", nullable = false)
    private String zipcode;

    @Column(name = "contact_house_number")
    private Integer house_nr;

    @Column(name = "contact_house_number_ext")
    private String house_nr_ext;
    
    @JsonIgnore
    @ManyToMany(mappedBy="organization", fetch = FetchType.EAGER)
//    @JoinTable(
//        name = "kikker_user_reseller",
//        joinColumns = {@JoinColumn(name = "reseller_id", referencedColumnName = "id")},
//        inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})

    @BatchSize(size = 20)
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

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

    public Organization name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Organization zipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getHouse_nr() {
        return house_nr;
    }

    public Organization house_nr(Integer house_nr) {
        this.house_nr = house_nr;
        return this;
    }

    public void setHouse_nr(Integer house_nr) {
        this.house_nr = house_nr;
    }

    public String getHouse_nr_ext() {
        return house_nr_ext;
    }

    public Organization house_nr_ext(String house_nr_ext) {
        this.house_nr_ext = house_nr_ext;
        return this;
    }

    public void setHouse_nr_ext(String house_nr_ext) {
        this.house_nr_ext = house_nr_ext;
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
        Organization kikker_organization = (Organization) o;
        if (kikker_organization.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kikker_organization.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", zipcode=" + zipcode + ", house_nr=" + house_nr
				+ ", house_nr_ext=" + house_nr_ext + ", users=" + users + ", getUsers()=" + getUsers() + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getZipcode()=" + getZipcode() + ", getHouse_nr()="
				+ getHouse_nr() + ", getHouse_nr_ext()=" + getHouse_nr_ext() + ", hashCode()=" + hashCode() + "]";
	}

}
