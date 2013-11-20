package au.uq.dke.comonviz.common.util;

import javax.swing.Icon;

import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.OWLIcons;
import org.protege.editor.owl.ui.renderer.OWLIconProvider;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.OWLObjectVisitorAdapter;

public class OWLIconProviderImpl extends OWLObjectVisitorAdapter implements OWLIconProvider {
	
    private Icon icon;

    private final Icon primitiveClassIcon = OWLIcons.getIcon("class.primitive.png");

    private final Icon definedClassIcon = OWLIcons.getIcon("class.defined.png");

    private final Icon objectPropertyIcon = OWLIcons.getIcon("property.object.png");

    private final Icon dataPropertyIcon = OWLIcons.getIcon("property.data.png");

    private final Icon annotationPropertyIcon = OWLIcons.getIcon("property.annotation.png");

    private final Icon individualIcon = OWLIcons.getIcon("individual.png");

    private final Icon dataTypeIcon = OWLIcons.getIcon("datarange.png");

    private final Icon ontologyIcon = OWLIcons.getIcon("ontology.png");

    private OWLModelManager owlModelManager;

    public OWLIconProviderImpl(OWLModelManager owlModelManager) {
        this.owlModelManager = owlModelManager;
    }

	public Icon getIcon() {
		return icon;
	}
	
    public Icon getIcon(OWLObject owlObject) {
        try {
            icon = null;
            owlObject.accept(this);
            return icon;
        }
        catch (Exception e) {
            return null;
        }
    }

    public void visit(OWLObjectIntersectionOf owlAnd) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDatatype owlDatatype) {
        icon = dataTypeIcon;
    }

    public void visit(OWLDataOneOf owlDataEnumeration) {
        icon = dataTypeIcon;
    }


    public void visit(OWLDataAllValuesFrom owlDataAllRestriction) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDataProperty owlDataProperty) {
        icon = dataPropertyIcon;
    }

    public void visit(OWLDataSomeValuesFrom owlDataSomeValuesFrom) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDataHasValue owlDataValueRestriction) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDifferentIndividualsAxiom owlDifferentIndividualsAxiom) {
        icon = individualIcon;
    }

    public void visit(OWLDisjointDataPropertiesAxiom owlDisjointDataPropertiesAxiom) {
        icon = dataPropertyIcon;
    }

    public void visit(OWLFunctionalObjectPropertyAxiom axiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLDisjointObjectPropertiesAxiom axiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLInverseObjectPropertiesAxiom axiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLHasKeyAxiom owlHasKeyAxiom) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDatatypeDefinitionAxiom owlDatatypeDefinitionAxiom) {
        icon = dataTypeIcon;
    }

    public void visit(OWLInverseFunctionalObjectPropertyAxiom axiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLObjectPropertyDomainAxiom axiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLEquivalentObjectPropertiesAxiom owlEquivalentObjectPropertiesAxiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLNegativeDataPropertyAssertionAxiom owlNegativeDataPropertyAssertionAxiom) {
        icon = individualIcon;
    }

    public void visit(OWLObjectPropertyRangeAxiom axiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLObjectPropertyAssertionAxiom owlObjectPropertyAssertionAxiom) {
        icon = individualIcon;
    }

    public void visit(OWLSubObjectPropertyOfAxiom axiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLNamedIndividual owlIndividual) {
        icon = individualIcon;
    }

    public void visit(OWLAnonymousIndividual individual) {
        icon = individualIcon;
    }

    public void visit(OWLObjectAllValuesFrom owlObjectAllRestriction) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLObjectMinCardinality desc) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLObjectExactCardinality desc) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLObjectMaxCardinality desc) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLObjectHasSelf desc) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDataMinCardinality desc) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDataExactCardinality desc) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDataMaxCardinality desc) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLObjectProperty owlObjectProperty) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLObjectSomeValuesFrom owlObjectSomeValuesFrom) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLObjectHasValue owlObjectValueRestriction) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLObjectComplementOf owlNot) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLOntology owlOntology) {
        icon = ontologyIcon;
    }

    public void visit(OWLObjectUnionOf owlOr) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDeclarationAxiom owlDeclarationAxiom) {
        owlDeclarationAxiom.getEntity().accept(this);
    }

    public void visit(OWLSubClassOfAxiom owlSubClassAxiom) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLNegativeObjectPropertyAssertionAxiom owlNegativeObjectPropertyAssertionAxiom) {
        icon = individualIcon;
    }

    public void visit(OWLAsymmetricObjectPropertyAxiom owlAntiSymmetricObjectPropertyAxiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLReflexiveObjectPropertyAxiom owlReflexiveObjectPropertyAxiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLDisjointClassesAxiom owlDisjointClassesAxiom) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDataPropertyDomainAxiom owlDataPropertyDomainAxiom) {
        icon = dataPropertyIcon;
    }

    public void visit(OWLDisjointUnionAxiom owlDisjointUnionAxiom) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLSymmetricObjectPropertyAxiom owlSymmetricObjectPropertyAxiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLDataPropertyRangeAxiom owlDataPropertyRangeAxiom) {
        icon = dataPropertyIcon;
    }

    public void visit(OWLFunctionalDataPropertyAxiom owlFunctionalDataPropertyAxiom) {
        icon = dataPropertyIcon;
    }

    public void visit(OWLEquivalentDataPropertiesAxiom owlEquivalentDataPropertiesAxiom) {
        icon = dataPropertyIcon;
    }

    public void visit(OWLEquivalentClassesAxiom owlEquivalentClassesAxiom) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLDataPropertyAssertionAxiom owlDataPropertyAssertionAxiom) {
        icon = individualIcon;
    }

    public void visit(OWLTransitiveObjectPropertyAxiom owlTransitiveObjectPropertyAxiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLIrreflexiveObjectPropertyAxiom owlIrreflexiveObjectPropertyAxiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLSubDataPropertyOfAxiom owlDataSubPropertyAxiom) {
        icon = dataPropertyIcon;
    }

    public void visit(OWLSameIndividualAxiom owlSameIndividualsAxiom) {
        icon = individualIcon;
    }
    
    public void visit(OWLClassAssertionAxiom owlClassAssertionAxiom) {
        icon = individualIcon;
    }

    public void visit(OWLSubPropertyChainOfAxiom axiom) {
        icon = objectPropertyIcon;
    }

    public void visit(OWLClass owlClass) {
        for (OWLOntology ont : owlModelManager.getActiveOntologies()) {
            if (owlClass.isDefined(ont)) {
                icon = definedClassIcon;
                return;
            }
        }
        icon = primitiveClassIcon;
    }

    public void visit(OWLObjectOneOf owlEnumeration) {
        icon = primitiveClassIcon;
    }

    public void visit(OWLAnnotationProperty owlAnnotationProperty) {
        icon = annotationPropertyIcon;
    }

    public void visit(OWLAnnotationAssertionAxiom owlAnnotationAssertionAxiom) {
        icon = annotationPropertyIcon;
    }

    public void visit(OWLSubAnnotationPropertyOfAxiom owlSubAnnotationPropertyOfAxiom) {
        icon = annotationPropertyIcon;
    }

    public void visit(OWLAnnotationPropertyDomainAxiom owlAnnotationPropertyDomainAxiom) {
        icon = annotationPropertyIcon;
    }

    public void visit(OWLAnnotationPropertyRangeAxiom owlAnnotationPropertyRangeAxiom) {
        icon = annotationPropertyIcon;
    }
}
