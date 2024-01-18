package com.etu1999.finals5;

import com.etu1999.finals5.entity.maladie.Maladie;
import com.etu1999.finals5.entity.maladie.MaladieSymptome;
import com.etu1999.finals5.entity.medicament.Medicament;
import com.etu1999.finals5.entity.medicament.Prescription;
import com.etu1999.finals5.entity.medicament.Traitement;
import com.etu1999.finals5.entity.patient.Patient;
import com.etu1999.finals5.entity.symptome.Symptome;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.List;

public class Dokotera {

    public static List<Maladie> getMaladies(List<Maladie> maladies, Patient patient){
        List<Maladie> val = new ArrayList<>();
        for(Maladie maladie : maladies){
            boolean state = false;
            for(MaladieSymptome maladieSymptome : maladie.getMaladieSymptomes()){
                double valeur = patient.getSymptomeValeur(maladieSymptome.getSymptome());
                if(maladieSymptome.getMin() >  valeur ||
                        maladieSymptome.getMax() < valeur){
                    state = false;
                    break;
                }else if(maladieSymptome.getMin() <=  valeur &&
                        maladieSymptome.getMax() >= valeur){
                    state = true;
                }
            }
            if(state)
                val.add(maladie);
        }
        return val;
    }

    /**
     * index manaraka ny index an symptome
     */
    public static PointValuePair getSolutionPatient(List<Symptome> symptomes, List<Medicament> medicaments, Patient patient){
        List<LinearConstraint> constraints = MakeConstraints(symptomes, medicaments, patient);
        LinearObjectiveFunction linearObjectiveFunction = MakeObjectiveFunction(medicaments);
        SimplexSolver solver = new SimplexSolver();
        return solver.optimize(linearObjectiveFunction, new LinearConstraintSet(constraints), GoalType.MINIMIZE, new NonNegativeConstraint(true));
    }

    public static Prescription getPrescription(List<Symptome> symptomes, List<Medicament> medicaments, Patient patient){
        Prescription prescription = new Prescription();
        prescription.setTraitements(getTraitements(symptomes, medicaments, patient));
        return prescription;
    }

    public static List<Traitement> getTraitements(List<Symptome> symptomes, List<Medicament> medicaments, Patient patient){
        PointValuePair solution = getSolutionPatient(symptomes, medicaments, patient);
        double[] doses = solution.getPoint();
        List<Traitement> traitements = new ArrayList<>();
        for (int i = 0; i < medicaments.size(); i++) {
            Traitement traitement = new Traitement();
            traitement.setMedicament(medicaments.get(i));
            traitement.setDose(doses[i]);
            traitements.add(traitement);
        }
        return traitements;
    }

    public static List<LinearConstraint> MakeConstraints(List<Symptome> symptomes, List<Medicament> medicaments, Patient patient){
        List<LinearConstraint> constraints = new ArrayList<>();
        for(Symptome symptome : symptomes){
            double rhs = patient.getSymptomeValeur(symptome);   // patient valeur
            double[] lhs = new double[symptomes.size()];          // left constraint
            for(int i = 0; i < medicaments.size(); i++){
                double soinValeur = medicaments.get(i).getSoinValeur(symptome);
                lhs[i] = soinValeur;
            }
            constraints.add(new LinearConstraint(lhs, Relationship.GEQ, rhs));
        }
        return constraints;
    }

    public static LinearObjectiveFunction MakeObjectiveFunction(List<Medicament> medicaments){
        double[] objectives = new double[medicaments.size()];
        for (int i = 0; i < objectives.length; i++) {
            objectives[i] = medicaments.get(i).getPrix();
        }
        return new LinearObjectiveFunction(objectives, 0);
    }

//    public static LinearConstraint MakeConstraint(List<Symptome> symptomes, Patient patient, Medicament medicament){
//        ArrayList<Double> lhs = new ArrayList<>();
//        //double patientValeur = patient.getSymptomeValeur(symptomes);
//        for(Symptome symptome : symptomes){
//            double soinValeur = medicament.getSoinValeur(symptome);
//            lhs.add(soinValeur);
//        }
//        //return new LinearConstraint(lhs.toArray(new Double[0]), Relationship.GEQ, patient.getSymptomePatients());
//        return null;
//    }
}
