package edutechInnovators.proyect.Assemblers;

import edutechInnovators.proyect.ControllerV2.MaterialControllerV2;
import edutechInnovators.proyect.Model.Material;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MaterialModelAssembler  implements RepresentationModelAssembler<Material, EntityModel<Material>> {
    @Override
    public  EntityModel <Material> toModel(Material material){
        return  EntityModel.of(material,
                linkTo(methodOn(MaterialControllerV2.class).getMaterialById(material.getId_material())).withSelfRel(),
                linkTo(methodOn(MaterialControllerV2.class).getAllMaterials()).withRel("material"));
    }

}
