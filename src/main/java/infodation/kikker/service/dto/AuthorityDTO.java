package infodation.kikker.service.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;

import infodation.kikker.domain.Authority;
import infodation.kikker.domain.Function;
import infodation.kikker.domain.Organization;
import infodation.kikker.domain.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An authority (a security role) used by Spring Security.
 */
public class AuthorityDTO {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private Set<String> functions = new HashSet<>();

    public Set<String> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<String> functions) {
		this.functions = functions;
	}

	public AuthorityDTO(Authority au) {
        this.name = au.getName();
        this.functions = au.getFunctions().stream()
            .map(Function::getName)
            .collect(Collectors.toSet());
    }
    @Override
    public String toString() {
        return "Authority{" +
            "name='" + name + '\'' +
            "}";
    }
}
