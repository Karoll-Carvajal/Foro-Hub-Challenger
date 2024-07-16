package com.example.foroHub.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITopicRepository  extends JpaRepository<Topic, Long> {
    @Query("""
            select t from Topico t
            order by
            t.fechaCreacion
            desc
            """)
    Page<Topic> sortByMostRecent(Pageable paginacion);
     boolean existsByTitulo(String titulo);
     boolean existsByMensaje(String mensaje);
}
