package com.example.myprojectraspi;

import com.pi4j.context.Context;
import com.pi4j.platform.Platform;
import com.pi4j.platform.Platforms;
import com.pi4j.provider.Providers;
import com.pi4j.registry.Registry;
import com.pi4j.util.Console;


//Class from Pi4j library to print info about raspberry pi details.
/**
 * Helper class to output info about the Pi4J {@link Context}.
 * <p>
 * After we initialize Pi4J, we can access the following core parts of the system:
 * <ul>
 *     <li>Platforms</li>
 *     <li>Platform (Default Runtime Platform)</li>
 *     <li>Providers (I/O Providers)</li>
 *     <li>Registry (I/O Registry)</li>
 * </ul>
 */
public class PrintInfo {

    /**
     * Pi4J Platforms.
     * <p>Platforms are intended to represent the hardware platform where Pi4J is running.  In most cases this will be
     * the 'RaspberryPi' platform, but Pi4J supports and extensible set of platforms thus additional platforms such as
     * 'BananaPi', 'Odroid', etc can be added.
     * </p>
     * <p>Platforms represent the physical layout of a system's hardware I/O
     * capabilities and what I/O providers the target platform supports.  For example, a 'RaspberryPi' platform supports
     * `Digital` inputs and outputs, PWM, I2C, SPI, and Serial but does not support a default provider for 'Analog'
     * inputs and outputs.</p>
     * <p>Platforms also provide validation for the I/O pins and their capabilities for the target hardware.</p>
     *
     * @param console {@link Console}
     * @param pi4j    {@link Context}
     */
    public static void printLoadedPlatforms(Console console, Context pi4j) {
        Platforms platforms = pi4j.platforms();

        // Let's print out to the console the detected and loaded
        // platforms that Pi4J detected when it was initialized.
        console.box("Pi4J PLATFORMS");
        console.println();
        platforms.describe().print(System.out);
        console.println();
    }

    /**
     * Pi4J Platform (Default Platform)
     * <p>A single 'default' platform is auto-assigned during Pi4J initialization based on a weighting value provided
     * by each platform implementation at runtime. Additionally, you can override this behavior and assign your own
     * 'default' platform anytime after initialization.</p>
     * <p>The default platform is a single platform instance from the managed platforms collection that will serve to
     * define the default I/O providers that Pi4J will use for each given I/O interface when creating and registering
     * I/O instances.</p>
     *
     * @param console {@link Console}
     * @param pi4j    {@link Context}
     */
    public static void printDefaultPlatform(Console console, Context pi4j) {
        Platform platform = pi4j.platform();

        // Let's print out to the console the detected and loaded
        // platforms that Pi4J detected when it was initialized.
        console.box("Pi4J DEFAULT PLATFORM");
        console.println();
        platform.describe().print(System.out);
        console.println();
    }

    /**
     * Pi4J Providers
     ??? * <??????p??????>Providers maj?? reprezentowa?? implementacje I/O i zapewnia?? dost??p do interfejs??w I/O dost??pnych na???
     ??? * system. Dostawcy "dostarczaj??" konkretne implementacje ??rodowisk uruchomieniowych interfejs??w I/O, takie jak:???
     * <ul>
     * <li>DigitalInput</li>
     * <li>DigitalOutput</li>
     * <li>AnalogInput</li>
     * <li>AnalogOutput</li>
     * <li>PWM</li>
     * <li>I2C</li>
     * <li>SPI</li>
     * <li>SERIAL</li>
     * </ul></p>
     ??? * <??????p??????>Ka??da platforma b??dzie mia??a domy??lny zestaw dostawc??w przypisanych do niej, aby s??u??y?? jako domy??lni dostawcy, kt??rzy???
     ??? * b??d?? u??ywane na sprz??towym I/O danej platformy. Jednak nie jeste?? ograniczony do dostawc??w, kt??rzy???
     ??? * platforma zapewnia, ??e mo??esz utworzy?? instancje interfejs??w I/O za pomoc?? dowolnego dostawcy, kt??ry zosta?? zarejestrowany na???
     ??? * System Pi4J. Dobrym tego przyk??adem s?? interfejsy I/O "AnalogInput" i "AnalogOutput". Ten???
     ??? * 'RaspberryPi' nie obs??uguje z natury analogowego sprz??tu I/O, ale z do????czonym przetwornikiem ADC (Analog to Digital)???
     ??? * Konwerter) lub uk??ad DAC (konwerter cyfrowo-analogowy) pod????czony do magistrali danych (I2C / SPI), kt??rego mo??esz u??y?????
     ??? * Pi4J do odczytu / zapisu do tych analogowych interfejs??w sprz??towych.< / p>???
     ??? * <??????p??????>Providers pozwalaj?? na ca??kowicie elastyczn?? i rozszerzaln?? infrastruktur?? umo??liwiaj??c?? stronom trzecim budowanie i???
     ??? * rozszerzy?? mo??liwo??ci Pi4J poprzez napisanie w??asnych bibliotek implementacji Dostawcy.</p>???
     *
     * @param console {@link Console}
     * @param pi4j    {@link Context}
     */
    public static void printProviders(Console console, Context pi4j) {
        Providers providers = pi4j.providers();

        // Let's print out to the console the detected and loaded
        // providers that Pi4J detected when it was initialized.
        console.box("Pi4J PROVIDERS");
        console.println();
        providers.describe().print(System.out);
        console.println();
    }

    /**
     * Pi4J Registry
     * <p>The registry stores the state of all the I/O managed by Pi4J.</p>
     *
     * @param console {@link Console}
     * @param pi4j    {@link Context}
     */
    public static void printRegistry(Console console, Context pi4j) {
        Registry registry = pi4j.registry();

        // Let's print out to the console the detected and loaded
        // I/O interfaces registered with Pi4J and included in the 'Registry'.
        console.box("Pi4J REGISTRY");
        console.println();
        registry.describe().print(System.out);
        console.println();
    }
}