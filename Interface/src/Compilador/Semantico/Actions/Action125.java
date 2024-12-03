/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compilador.Semantico.Actions;
import Compilador.Semantico.*;
import pkggals.*;

/**
 *
 * @author lbnitsche
 */
public class Action125 implements SemanticAction {

    @Override
    public void execute(Token token, SemanticContext context) throws SemanticError {
        if (context.getPilhaTipos().size() < 2) {
            throw new SemanticError("Erro semântico: tipos insuficientes na pilha para a operação aritmética binária '*'.",
                    token.getPosition());
        }

        String tipoOperando2 = context.getPilhaTipos().pop();
        String tipoOperando1 = context.getPilhaTipos().pop();

        String tipoResultante;
        if (tipoOperando1.equals("int64") && tipoOperando2.equals("int64")) {
            tipoResultante = "int64";
        } else if ((tipoOperando1.equals("int64") || tipoOperando1.equals("float64")) &&
                   (tipoOperando2.equals("int64") || tipoOperando2.equals("float64"))) {
            tipoResultante = "float64";
        } else {
            throw new SemanticError("Erro semântico: tipos incompatíveis para a operação aritmética '*'.",
                    token.getPosition());
        }

        context.getPilhaTipos().push(tipoResultante);
        context.getCodigoObjeto().append("mul\n");
    }
}

