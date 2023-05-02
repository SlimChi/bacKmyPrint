package fr.rt.MyPrintRed.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utilisateur", schema = "public", catalog = "MYPRINTTEST")
public class Utilisateur implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_utilisateur")
    private int idUtilisateur;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "prenom")
    private String prenom;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "mot_de_passe")
    private String password;
    @Basic
    @Column(name = "telephone")
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private transient Role role;

    @OneToMany(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur")
    private transient List<Adresse> adresse;


    private boolean active = false;

    public Utilisateur(Integer idUtilisateur,String nom, String prenom, String email, String telephone,String password) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }

    public Utilisateur(String nom, String prenom, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    public Utilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getLibelle()));
    }
    public Integer getId() {
        return idUtilisateur;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }


 }
