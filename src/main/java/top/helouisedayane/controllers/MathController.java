package top.helouisedayane.controllers;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    // Soma de números em array
    @GetMapping("/sum")
    public Double sum(@RequestParam double[] numbers) throws Exception {
        validateNumbers(numbers);
        return Arrays.stream(numbers).sum();
    }

    // Subtração de números em array
    @GetMapping("/sub")
    public Double sub(@RequestParam double[] numbers) throws Exception {
        validateNumbers(numbers);
        return Arrays.stream(numbers).reduce((a, b) -> a - b).orElseThrow(() -> new UnsupportedOperationException("Array vazio ou inválido"));
    }

    // Multiplicação de números em array
    @GetMapping("/multiplication")
    public Double multiplication(@RequestParam double[] numbers) throws Exception {
        validateNumbers(numbers);
        return Arrays.stream(numbers).reduce(1.0, (a, b) -> a * b);
    }

    // Divisão de números em array
    @GetMapping("/div")
    public Double div(@RequestParam double[] numbers) throws Exception {
        if (numbers.length < 2) {
            throw new UnsupportedOperationException("É necessário passar ao menos dois números.");
        }
        validateNumbers(numbers);

        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                throw new UnsupportedOperationException("Divisão por zero não é permitida!");
            }
            result /= numbers[i];
        }
        return result;
    }

    // Média de números em array
    @GetMapping("/average")
    public Double average(@RequestParam double[] numbers) throws Exception {
        if (numbers.length == 0) {
            throw new UnsupportedOperationException("O array não pode estar vazio.");
        }
        validateNumbers(numbers);
        return Arrays.stream(numbers).average().orElseThrow(() -> new UnsupportedOperationException("Erro no cálculo da média"));
    }

    // Raiz quadrada de um número
    @GetMapping("/sqrt")
    public Double sqrt(@RequestParam double number) throws Exception {
        if (number < 0) {
            throw new UnsupportedOperationException("Não pode ser número negativo");
        }
        return Math.sqrt(number);
    }

    // Método auxiliar para validar números no array
    private void validateNumbers(double[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = replaceCommaWithDot(numbers[i]);
            if (Double.isNaN(numbers[i]) || Double.isInfinite(numbers[i])) {
                throw new UnsupportedOperationException("Todos os números devem ser válidos.");
            }
        }
    }

    // Método auxiliar para substituir vírgula por ponto
    private double replaceCommaWithDot(double number) {
        String numStr = String.valueOf(number);
        if (numStr.contains(",")) {
            numStr = numStr.replace(",", ".");
        }
        return Double.parseDouble(numStr);
    }
}
