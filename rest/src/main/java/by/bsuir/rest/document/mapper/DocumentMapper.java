package by.bsuir.rest.document.mapper;

import by.bsuir.document.model.document.Document;
import by.bsuir.rest.common.mapper.EntityMapper;
import by.bsuir.rest.document.model.DocumentEntity;
import org.mapstruct.Mapper;

/**
 * @author Vladislav Novitskiy
 */
@Mapper(componentModel = "spring")
public interface DocumentMapper extends EntityMapper<DocumentEntity, Document> {
}
