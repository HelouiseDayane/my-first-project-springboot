package top.helouisedayane.controllers;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/math")
public class MathController 
{

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum (
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Coloque um numero");
        return Double.parseDouble(numberOne) + Double.parseDouble(numberTwo);   
    }


    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    public Double sub (
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Coloque um numero");
        return Double.parseDouble(numberOne) - Double.parseDouble(numberTwo);   
    }

    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication (
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Coloque um numero");
        return Double.parseDouble(numberOne) * Double.parseDouble(numberTwo);   
    }

    @RequestMapping("/div/{numberOne}/{numberTwo}")
    public Double div (
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception{

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Coloque um numero");
     
        double num1 = Double.parseDouble(numberOne);
        double num2 = Double.parseDouble(numberTwo);

        if (num2 == 0) { 
            throw new ArithmeticException("Divisão por zero não é permitida!");
        }
    
        return num1 / num2;  
    }

     @GetMapping("/average")
    public Double average(@RequestParam double[] numbers) throws Exception {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("O array não pode estar vazio.");
        }

        // Verificando se todos os números são válidos
        for (double num : numbers) {
            if (!isValidNumber(num)) {
                throw new IllegalArgumentException("Coloque um número válido.");
            }
        }

        double sum = Arrays.stream(numbers).sum();
        return sum / numbers.length;
    }

    @RequestMapping("/sqrt}")
    public Double sqrt (
        @PathVariable("numberOne") String numberOne
    ) throws Exception{
        if(!isNumeric(numberOne)) throw new UnsupportedOperationException("Coloque um numero");

        double num1 = Double.parseDouble(numberOne);
        if(num1 <0) throw new UnsupportedOperationException("Não pode ser numero negativo");
        return Math.sqrt(num1);   
    }



    private boolean isNumeric(String strNumber){
        if(strNumber == null || strNumber.isEmpty()) return false;

        String number = strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
        
    }

    private boolean isValidNumber(double num) {
        return !Double.isNaN(num) && !Double.isInfinite(num);
    }

    
}
