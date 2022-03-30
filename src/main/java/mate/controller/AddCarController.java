package mate.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.model.Car;
import mate.model.Manufacturer;
import mate.service.CarService;
import mate.service.ManufacturerService;

public class AddCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private final CarService carService = (CarService) injector.getInstance(CarService.class);
    private final ManufacturerService manufacturerService = (ManufacturerService)
            injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/cars/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String model = req.getParameter("model");
        String manufacturerName = req.getParameter("manufacturer_name");
        Manufacturer manufacturer = getManufacturerByName(manufacturerName).get();
        Car car = new Car();
        car.setModel(model);
        car.setManufacturer(manufacturer);
        carService.create(car);
    }

    private Optional<Manufacturer> getManufacturerByName(String name) {
        List<Manufacturer> manufacturers = manufacturerService.getAll();
        for (Manufacturer manufacturer: manufacturers) {
            if (manufacturer.getName().equals(name)) {
                return Optional.ofNullable(manufacturer);
            }
        }
        return Optional.empty();
    }
}
