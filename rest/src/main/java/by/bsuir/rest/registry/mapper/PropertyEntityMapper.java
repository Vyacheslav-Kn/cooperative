package by.bsuir.rest.registry.mapper;

import by.bsuir.registry.model.Property;
import by.bsuir.rest.common.mapper.EntityMapper;
import by.bsuir.rest.registry.model.PropertyEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class PropertyEntityMapper implements EntityMapper<PropertyEntity, Property> {

    private final EntityMapper<PropertyEntity.PropertyOwner, Property.PropertyOwner> ownerEntityMapper = new PropertyOwnerEntityMapper();
    private final EntityMapper<PropertyEntity.HistoryPropertyOwner, Property.HistoryPropertyOwner> historyOwnerEntityMapper = new HistoryPropertyOwnerEntityMapper();

    @Override
    public PropertyEntity toDto(Property entity) {
        return new PropertyEntity(
                entity.getId(),
                entity.getInventoryNumber(),
                entity.getSquare(),
                entity.getPtn(),
                entity.getOwners() == null ? Collections.emptyList() : entity.getOwners().stream().map(ownerEntityMapper::toDto).collect(Collectors.toList()),
                entity.getHistoryOwners() == null ? Collections.emptyList() : entity.getHistoryOwners().stream().map(historyOwnerEntityMapper::toDto).collect(Collectors.toList())
        );
    }

    @Override
    public Property fromDto(PropertyEntity dto) {
        return new Property(
                dto.getId(),
                dto.getInventoryNumber(),
                dto.getSquare(),
                dto.getOwners() == null ? Collections.emptyList() : dto.getOwners().stream().map(ownerEntityMapper::fromDto).collect(Collectors.toList()),
                Collections.emptyList(),
                dto.getPtn()
        );
    }

    private static final class PropertyOwnerEntityMapper implements EntityMapper<PropertyEntity.PropertyOwner, Property.PropertyOwner> {

        @Override
        public PropertyEntity.PropertyOwner toDto(Property.PropertyOwner entity) {
            return new PropertyEntity.PropertyOwner(
                    entity.getPersonId(),
                    entity.getStartDate(),
                    entity.getOwningPercent(),
                    entity.getNumberAuthority()
            );
        }

        @Override
        public Property.PropertyOwner fromDto(PropertyEntity.PropertyOwner dto) {
            return new Property.PropertyOwner(
                    dto.getPersonId(),
                    dto.getStartDate(),
                    dto.getOwningPercent(),
                    dto.getNumberAuthority()
            );
        }

    }

    private static final class HistoryPropertyOwnerEntityMapper implements EntityMapper<PropertyEntity.HistoryPropertyOwner, Property.HistoryPropertyOwner> {

        @Override
        public PropertyEntity.HistoryPropertyOwner toDto(Property.HistoryPropertyOwner entity) {
            return new PropertyEntity.HistoryPropertyOwner(
                    entity.getPersonId(),
                    entity.getStartDate(),
                    entity.getOwningPercent(),
                    entity.getEndDate(),
                    entity.getNumberAuthority()
            );
        }

        @Override
        public Property.HistoryPropertyOwner fromDto(PropertyEntity.HistoryPropertyOwner dto) {
            return new Property.HistoryPropertyOwner(
                    dto.getPersonId(),
                    dto.getStartDate(),
                    dto.getOwningPercent(),
                    dto.getNumberAuthority(),
                    dto.getEndDate()
            );
        }

    }
}
