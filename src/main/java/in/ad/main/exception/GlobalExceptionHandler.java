package in.ad.main.exception;
import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Resource Not Found
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(
            ResourceNotFoundException ex,
            Model model) {

        model.addAttribute("errorTitle", "Resource Not Found");
        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

    /*
     * File Upload Exception
     */
    @ExceptionHandler(FileStorageException.class)
    public String handleFileException(
            FileStorageException ex,
            Model model) {

        model.addAttribute("errorTitle", "File Upload Error");
        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

    /*
     * IOException
     */
    @ExceptionHandler(IOException.class)
    public String handleIOException(
            IOException ex,
            Model model) {

        model.addAttribute("errorTitle", "File Error");
        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

    /*
     * Validation Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidation(
            MethodArgumentNotValidException ex,
            Model model) {

        String error = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        model.addAttribute("errorTitle", "Validation Error");
        model.addAttribute("errorMessage", error);

        return "error";
    }

    /*
     * Illegal Argument
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(
            IllegalArgumentException ex,
            Model model) {

        model.addAttribute("errorTitle", "Invalid Request");
        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

    /*
     * Runtime Exception
     */
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntime(
            RuntimeException ex,
            Model model) {

        model.addAttribute("errorTitle", "Runtime Error");
        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

    /*
     * Any Other Exception
     */
    @ExceptionHandler(Exception.class)
    public String handleException(
            Exception ex,
            Model model) {

        model.addAttribute("errorTitle", "Something Went Wrong");
        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

}