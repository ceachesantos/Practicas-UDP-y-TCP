`using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.UIElements; // Asegúrate de agregar esta línea para acceder a la clase Slider

public class DMXWire : MonoBehaviour
{
 
    // Referencia al objeto que contiene el script DMXProtocol
    private GameObject dmxProtocolObject;
    private DMXProtocol dmxProtocol;
    public UnityEngine.UI.Button On_OffButton;
    public UnityEngine.UI.Button All_MaxButton;
    public UnityEngine.UI.Button RMax;
    public UnityEngine.UI.Button BMax;
    public UnityEngine.UI.Button GMax;
/*     public UnityEngine.UI.Button UP;
    public UnityEngine.UI.Button DW;
    public UnityEngine.UI.Button LH;
    public UnityEngine.UI.Button RH;
    public UnityEngine.UI.Button ST; */
    private bool On_OffButtonClicked = false;

    //---------------------------------
    private enum PanTiltDirection {
        None,
        Up,
        Down,
        Left,
        Right,
        Init
    }

    private PanTiltDirection panTiltDirection = PanTiltDirection.None;

    public void clickUp() {
        panTiltDirection = PanTiltDirection.Up;
    }

    public void releaseUp() {
        if (panTiltDirection == PanTiltDirection.Up)
            panTiltDirection = PanTiltDirection.None;
    }

    public void clickDw() {
        panTiltDirection = PanTiltDirection.Down;
    }

    public void releaseDw() {
        if (panTiltDirection == PanTiltDirection.Down)
            panTiltDirection = PanTiltDirection.None;
    }

    public void clickLh() {
        panTiltDirection = PanTiltDirection.Left;
    }

    public void releaseLh() {
        if (panTiltDirection == PanTiltDirection.Left)
            panTiltDirection = PanTiltDirection.None;
    }

    public void clickRh() {
        panTiltDirection = PanTiltDirection.Right;
    }

    public void releaseRh() {
        if (panTiltDirection == PanTiltDirection.Right)
            panTiltDirection = PanTiltDirection.None;
    }

    public void clickInit() {
        panTiltDirection = PanTiltDirection.Init;
    }

    public void releaseInit() {
        if (panTiltDirection == PanTiltDirection.Init)
            panTiltDirection = PanTiltDirection.None;
    }


    // Start is called before the first frame update
    void Start()
    {
        On_OffButton.onClick.AddListener(() => ButtonClicked("OnOffButton"));
        All_MaxButton.onClick.AddListener(() => ButtonClicked("AllMaxButton"));
        RMax.onClick.AddListener(() => ButtonClicked("RMax"));
        GMax.onClick.AddListener(() => ButtonClicked("GMax"));
        BMax.onClick.AddListener(() => ButtonClicked("BMax"));
        // Añadir listener para el evento OnPointerDown (presionar)
        //UP.onClick.AddListener(() => panTiltButtons("UP"));
        //DW.onClick.AddListener(() => panTiltButtons("DW"));
        //LH.onClick.AddListener(() => panTiltButtons("LH"));
        //RH.onClick.AddListener(() => panTiltButtons("RH"));
        //ST.onClick.AddListener(() => panTiltButtons("IN"));





        // Encuentra el objeto por su nombre. Asegúrate de que el nombre sea correcto.
        dmxProtocolObject = GameObject.Find("GameManager");

        // Verifica si se encontró el objeto y el script
        if (dmxProtocolObject != null)
        {
            dmxProtocol = dmxProtocolObject.GetComponent<DMXProtocol>();
        }
        else
        {
            Debug.LogError("Objeto DMXProtocol no encontrado.");
        }
    }

    // Update is called once per frame
    void Update()
    {
        switch (panTiltDirection) {
        case PanTiltDirection.Up:
            panTiltButtons("UP");
            break;
        case PanTiltDirection.Down:
            panTiltButtons("DW");
            break;
        case PanTiltDirection.Left:
            panTiltButtons("LH");
            break;
        case PanTiltDirection.Right:
            panTiltButtons("RH");
            break;
            case PanTiltDirection.Init:
            panTiltButtons("IN");
            break;
        default:
            panTiltButtons("ST");
            break;
    }
       
    }

    // Función para botones
    public void ButtonClicked(string message)
    {
        dmxProtocol.setDMXChannel(0);
        if (message.Equals("OnOffButton"))
        {
            if (On_OffButtonClicked == false)
            {
                On_OffButtonClicked=true;
                dmxProtocol.setDMXValue(1);
             }
            else
            {
                On_OffButtonClicked=false;
                dmxProtocol.setDMXValue(0);
            }

        }
        if (message.Equals("AllMaxButton"))
        {
      
         dmxProtocol.setDMXValue(2);
   

        }
        if (message.Equals("RMax"))
        {
            dmxProtocol.setDMXValue(3);
        }

        if (message.Equals("GMax"))
        {
            dmxProtocol.setDMXValue(4);
        }
        if (message.Equals("BMax"))
        {
            dmxProtocol.setDMXValue(5);
        }
    }

    // Función para cambiar el valor del slider rojo
    public void ChangeRedSlider(float value)
    {
        dmxProtocol.setDMXChannel(1);
        dmxProtocol.setDMXValue((int)value);
        //Debug.Log("Valor del slider rojo: " + value);
    }

    // Función para cambiar el valor del slider verde
    public void ChangeGreenSlider(float value)
    {
        dmxProtocol.setDMXChannel(2);
        dmxProtocol.setDMXValue((int)value);
        //Debug.Log("Valor del slider verde: " + value);
    }

    // Función para cambiar el valor del slider azul
    public void ChangeBlueSlider(float value)
    {
        dmxProtocol.setDMXChannel(3);
        dmxProtocol.setDMXValue((int)value);
        //Debug.Log("Valor del slider azul: " + value);
    }

    public void ChangeIntensitySlider(float value)
    {
        dmxProtocol.setDMXChannel(4);
        dmxProtocol.setDMXValue((int)value);
        //Debug.Log("Valor de la intensidad: " + value);
    }

    public void ChangeShutterSlider(float value)
    {
        dmxProtocol.setDMXChannel(5);
        dmxProtocol.setDMXValue((int)value);
        //Debug.Log("Valor del shutter: " + value);
    }

    public void panTiltButtons(string message)
    {
        dmxProtocol.setDMXChannel(6);
        if (message.Equals("UP")) dmxProtocol.setDMXValue(0);
        if (message.Equals("DW")) dmxProtocol.setDMXValue(1);
        if (message.Equals("LH")) dmxProtocol.setDMXValue(2);
        if (message.Equals("RH")) dmxProtocol.setDMXValue(3);
        if (message.Equals("ST")) dmxProtocol.setDMXValue(4);
        if (message.Equals("IN")) dmxProtocol.setDMXValue(5);

    }

}