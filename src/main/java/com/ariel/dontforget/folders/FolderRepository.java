package com.ariel.dontforget.folders;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface FolderRepository extends JpaRepository<Folder,Long>{
    Optional<Folder> findFolderByName(String name);
    Optional<Folder> findFolderById(Long id);
}

