package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.text.NumberFormat;
import java.util.List;

public class MainFormController {

    public AnchorPane pneContainer;
    public JFXButton btnSelectFile;
    public JFXButton btnSelectDirectory;
    public Label lblSelectedFile;
    public Label lblBrowsedDirectory;
    public JFXButton btnBrowseDirectory;
    public Label lblProgress;
    public Label lblSize;
    public Rectangle pgbBar;
    public Rectangle pgbContainer;
    public Button btnCopy;
    private List<File> srcFiles;
    private File srcDirectory;
    private File destDir;

    public void initialize() {
        FadeTransition fd = new FadeTransition(Duration.millis(1500),pneContainer);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();
        btnCopy.setDisable(true);
    }

    public void btnSelectFile_OnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Select files to copy");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files (*.*)", "*.*"));
        srcFiles = fileChooser.showOpenMultipleDialog(btnSelectFile.getScene().getWindow());
        if (srcFiles != null) {
            srcDirectory = null;
            double size = 0;
            if (srcFiles.size() > 1) {
                for (File file : srcFiles) {
                    size += file.length();
                }
                lblSelectedFile.setText("Multiple files have been selected, size: " + formatNumber(size/1024.0) + " Kb");
            }
            else if (srcFiles.size() == 1) {
                lblSelectedFile.setText("File name: " + srcFiles.get(0).getName() + ", size: " + formatNumber(srcFiles.get(0).length()/1024.0) + " Kb");
            }
        }
        else lblSelectedFile.setText("No file/directory is selected");
        btnCopy.setDisable(srcFiles == null || destDir == null);
    }

    public void btnSelectDirectory_OnAction(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        directoryChooser.setTitle("Select a directory to copy");
        srcDirectory = directoryChooser.showDialog(btnBrowseDirectory.getScene().getWindow());
        if (srcDirectory != null) {
            srcFiles = null;
            double size = 0;
            File[] files = srcDirectory.listFiles();
            for (File file : files) {
                size += file.length();
            }
            lblSelectedFile.setText("Folder name: " + srcDirectory.getName() + ", inner file size: " + formatNumber(size/1024.0) + " Kb");
        }
        else lblSelectedFile.setText("No file/directory is selected");
        btnCopy.setDisable(srcDirectory == null || destDir == null);
    }

    public void btnBrowseDirectory_OnAction(ActionEvent actionEvent) {

    }

    public void btnCopy_OnAction(ActionEvent actionEvent) {

    }

    private String formatNumber(double input) {
        NumberFormat number = NumberFormat.getNumberInstance();
        number.setGroupingUsed(true);
        number.setMinimumFractionDigits(2);
        number.setMaximumFractionDigits(2);
        return number.format(input);
    }
}
