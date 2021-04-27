package com.gft.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.mvc.model.Notebook;

public interface NotebookRepository extends JpaRepository<Notebook, Long> { }