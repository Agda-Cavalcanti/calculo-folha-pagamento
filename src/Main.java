import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {

    static final float primeira_faixa = 97.65F;
    static final float segunda_faixa = 114.24F;

    static final float terceira_faixa = 154.28F;

    static final float quarta_faixa = 511.07F;
    //IRRF
    static final float segunda_irpf = 142.80F;

    static final float terceira_irpf = 354.80F;

    static final float quarta_irpf = 636.13F;

    static final float quinta_irpf = 869.36F;


    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.print("Nome: ");
        String nome = console.nextLine();
        System.out.println();



        System.out.print("Digite seu CPF: ");
        String CPF = console.next();
        System.out.println();

        System.out.print("Cargo ocupado: ");
        String cargo = console.next();
        System.out.println();

        System.out.print("Data de admissão: ");
        String admissao = console.next();
        System.out.println();

        System.out.print("Mês de referência: ");
        String referencia = console.next();
        System.out.println();

        System.out.print("Valor do salário base: ");     //SALÁRIO BASE
        float salario_base = console.nextFloat();
        System.out.println();


        System.out.print("Horas trabalhadas por dia (Considere o período de descanso): ");
        int horas_diarias = console.nextInt();

        System.out.print("Quantidade de dias trabalhados por semana (Considere o período de descanso):  ");
        int dias_trabalhados = console.nextInt();

        int jornada_semanal = horas_diarias * dias_trabalhados;     //CÁLCULO
        int jornada_mensal = jornada_semanal * 5;
        float salario_hora = (float) salario_base/jornada_mensal;
        System.out.println();


        String entrada = "0";  //PERICULOSIDADE
        System.out.print("Possui direito de periculosidade? ");
        entrada = console.next().toUpperCase().trim();
        System.out.println();



        System.out.print("Possui o adicional de insalubridade? ");
        String insalubridade = console.next().toUpperCase().trim(); //POSSUI INSALUBRIDADE
        float calculo_insalubridade = 0;
        System.out.println();


        if(insalubridade.charAt(0) == 'S') {            //INSALUBRIDADE
            System.out.println("Qual a porcentagem de adicional? 10, 20 ou 40? ");
            int grau_insalubridade = console.nextInt();

            if(grau_insalubridade ==10){
                calculo_insalubridade = (salario_base * 10)/100;

            } else if (grau_insalubridade == 20) {
                calculo_insalubridade = (salario_base * 20)/100;

            } else if (grau_insalubridade == 40) {
                calculo_insalubridade = (salario_base * 40)/100;

            }
        }  else {
            System.out.print("Não possui adicional de insalubridade");
        }


        //VALE TRANSPORTE
        System.out.print("Valor do vale transporte em reais: ");
        float vale_transporte = console.nextFloat();
        float porcentagem_transporte = 0;
        float calculofinal_transporte = 0;

        porcentagem_transporte = (salario_base) * 6/100;
        if(vale_transporte<porcentagem_transporte){
            calculofinal_transporte = vale_transporte;
        } else if (vale_transporte>=porcentagem_transporte) {
            calculofinal_transporte = porcentagem_transporte;
        }

        //VALE ALIMENTAÇÃO
        System.out.print("Qual o valor do vale alimentação diário: "); //POSSUI VALE ALIMENTAÇÃO??
        float vale_alimentacao = console.nextFloat();
        System.out.print("Quantos dias trabalhados por mês: ");
        int dias_pormes = console.nextInt();
        float calculo_aliemntacao = vale_alimentacao*dias_pormes;



        //RELATÓRIO



        System.out.println("\nRELATÓRIO:");
        System.out.println("Nome do colaborador: "+ nome);
        System.out.println("CPF: " + CPF);
        System.out.println("Data de admissão: "+admissao);
        System.out.println("Mês de referência: " + referencia);
        System.out.println("Cargo do colaborador: " + cargo);
        System.out.println("Salário do colaborador:R$"+ salario_base + " reais\n");



        System.out.println("PROVENTOS:");


        float calculo_periculosidade = (salario_base * 30)/100;
        if(entrada.charAt(0) == 'S') {     //PERICULOSIDADE
            System.out.println("Periculosidade: " + calculo_periculosidade);
        }else {
            System.out.println("Não possui periculosidade");
        }

        System.out.println("Insalubridade: " + calculo_insalubridade);


        //VALE ALIMENTAÇÃO
        System.out.println("Vale alimentação: " + calculo_aliemntacao);



        float salario_bruto = salario_base + calculo_aliemntacao + calculo_periculosidade + calculo_insalubridade;

        //DESCONTOS

        //VALE TRANSPORTE
        System.out.println("Vale transporte: " + calculofinal_transporte);


        //INSS
        float calculo = 0;
        float inss = 0;

        if (salario_bruto <= 1302.0) {
            inss = (float) (salario_bruto*0.075);
        } else if (salario_bruto <= 2571.29) {

            calculo = (float) ((salario_bruto - 1302.00) * 0.09);
            inss = (float) (calculo + primeira_faixa);
        } else if (salario_bruto <= 3856.94) {

            calculo = (float) ((salario_bruto - 2571.29)*0.12);
            inss = (float) (calculo + primeira_faixa + segunda_faixa);
        } else if (salario_bruto <= 7507.49) {
            calculo = (float) ((salario_bruto - 3856.94)* 0.14);
            inss = (float) (calculo + primeira_faixa + segunda_faixa + terceira_faixa);
        } else if (salario_bruto > 7507.49) {
            inss =  quarta_faixa + terceira_faixa + segunda_faixa +  primeira_faixa;
        }

        float aliquota_efetiva =  (inss/ salario_bruto)* 100;
        System.out.println("INSS: " + df.format(inss));
        System.out.println("Alíquota efetiva: "+ df.format(aliquota_efetiva) + "%");


        //FGTS
        float fgts = (float) (salario_bruto * 0.08);
        System.out.println("FGTS: "+ fgts);


        //IRRF
        float base_deducoes = salario_bruto - vale_alimentacao - vale_transporte - inss - fgts;
        float incidencia = 0;
        float irrf = 0;


        if(base_deducoes <= 1903.98){
            System.out.println("Isento");
        } else if ( base_deducoes <=2826.65) {
            incidencia = (float) (base_deducoes*0.075);
            irrf = incidencia - segunda_irpf;
        } else if (base_deducoes<= 3751.05) {
            incidencia = (float) (base_deducoes*0.15);
            irrf = incidencia - terceira_irpf;
        } else if (base_deducoes<= 4664.68) {
            incidencia = (float) (base_deducoes*0.225);
            irrf = incidencia - quarta_irpf;
        } else if (base_deducoes>= 4664.68) {
            incidencia = (float) (base_deducoes*0.275);
            irrf = incidencia - quinta_irpf;
        }
        float aliquiota_irrf = (irrf/salario_bruto) * 100 ;


        System.out.println("IRRF: " + df.format(irrf));

        System.out.println("Alíquota efetiva: " + df.format(aliquiota_irrf));

        System.out.println("JORNADAS E SALÁRIO POR HORA");
        System.out.println("Jornada semanal: " +jornada_semanal + " horas");
        System.out.println("Jornada mensal: " + jornada_mensal + " horas");
        System.out.println("Salário por hora: R$"+salario_hora +" por hora");

        //BRUTO
        System.out.println("Salário bruto: R$" + salario_bruto + " reais");

        float salario_liquido =  salario_bruto - calculofinal_transporte - inss - fgts - irrf ;
        //LÍQUIDO
        System.out.println("Salário Líquido: R$" + salario_liquido + " reais");

        float base_calculo = salario_bruto - inss - fgts - irrf;

        System.out.println("Base de cálculo: R$" + base_calculo + " reais");
    }
}