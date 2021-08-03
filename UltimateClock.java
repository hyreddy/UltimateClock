/**
 * @author Hrishikesh Yeluru
 *  This class takes the time from your computer and displays it and the time in 23 other cities in different time 
 *  zones. On the clock it displays the time and date of a particular city. You can toggle between the different 
 *  cities by typing 'A' or 'a' and 'D' or 'd' on the keyboard. 'A' shifts the clock time down an hour whereas 
 *  'D' shifts the time up an hour. You can also type 'S' or 's' to switch the clock from a digital clock to a 
 *  analog clock and vice versa.
 * */

import java.util.*;

import processing.core.PApplet;

public class UltimateClock extends PApplet{
	
	// Initializes all the variables used in the class
	int dva;
	int offset_hours;
	String zone;
	
	Calendar cal;
	TimeZone tz;
	int day;
	int month;
	int year;
	
	float h, m, s;
	int fontSize;
	
	int cx, cy;
	float secondsRadius;
	float minutesRadius;
	float hoursRadius;
	float clockDiameter;
	float markerRadius;
	
	public void settings() {
		size(800,800);
	}
	
	public void setup() {
		stroke(255);
		
		// Sets the digital clock as the default
		dva = 1;
		
		// Retrieves the time from the computer and computes the offset from UTC time
		TimeZone tz_here = TimeZone.getDefault();
		double offset = tz_here.getOffset(System.currentTimeMillis());
		offset_hours = (int) (offset/3.6e+6);
		zone = "GMT" + offset_hours;
		
		// Creates a TimeZone and Calendar object with the information retrieved from the computer
		tz = java.util.TimeZone.getTimeZone(zone);
		cal = java.util.Calendar.getInstance(tz);
		
		// Sets the formatting for the analog clock and its hands
		int radius = min(500, 500) / 2;
		secondsRadius = (float) (radius * 0.65);
		minutesRadius = (float) (radius * 0.60);
		hoursRadius = (float) (radius * 0.40);
		clockDiameter = (float) (radius * 1.8);
		markerRadius = (float) (radius * 0.8);
		fontSize = 80;

		// Determines the positioning of the analog clock
		cx = width / 2;
		cy = height / 2;
	}
	
	// This gets the hour, minute, and seconds for a time based on its timezone
	public void getTime() {
		if(offset_hours <0)
			zone = "GMT" + offset_hours;
		else
			zone = "GMT+" + offset_hours;
		tz = java.util.TimeZone.getTimeZone(zone);
		cal = java.util.Calendar.getInstance(tz);
					
	    h = cal.get(java.util.Calendar.HOUR_OF_DAY);
	    m = cal.get(java.util.Calendar.MINUTE);
	    s = cal.get(java.util.Calendar.SECOND);    
	}
	
	// This method returns the date for each time based on its UTC offset
	public void getDate() {
		day = cal.get(java.util.Calendar.DATE);
		month = cal.get(java.util.Calendar.MONTH);
		year = cal.get(java.util.Calendar.YEAR);
		
		textSize(35);
		int dlx = 400;
		int dly = 700;
		switch(month) {
        case 0 :
        	text("January " + day + ", " + year, dlx, dly); 
           break;
        case 1 :
        	text("February " + day + ", " + year, dlx, dly); 
            break;
        case 2 :
        	text("March " + day + ", " + year, dlx, dly);
            break;
        case 3 :
        	text("April " + day + ", " + year, dlx, dly);
            break;
        case 4 :
        	text("May " + day + ", " + year, dlx, dly);
            break;
        case 5 :
        	text("June " + day + ", " + year, dlx, dly);
            break;
        case 6 :
        	text("July " + day + ", " + year, dlx, dly);
            break;
        case 7 :
        	text("August " + day + ", " + year, dlx, dly);
            break;
        case 8 :
        	text("September " + day + ", " + year, dlx, dly);
            break;
        case 9 :
        	text("October " + day + ", " + year, dlx, dly);
            break;
         case 10 :
        	 text("November " + day + ", " + year, dlx, dly);
             break;
         case 11 :
        	 text("December " + day + ", " + year, dlx, dly);
             break;
         }
	}
	
	// This method determines the value of offset_hours and dva based on the keys inputed by the user
	public void keyPressed() {
		if (key == 'A' || key == 'a') {
			offset_hours--;
		}
		else if (key == 'D' || key == 'd') {
			offset_hours++;
		}
		else if (key == 'S' || key == 's') {
			dva++;
		}
		else;	
		
		// Makes -12, 12 the bound of the offset_hours variable
		if (offset_hours == 13)
			offset_hours= -12;
		else if (offset_hours == -13)
			offset_hours = 12;
	}
	
	// This method displays the time for the digital clock
	public void display() {
	    textSize(fontSize);
	    textAlign(CENTER);
	    
	    text (nf((int)h,2) + ":" + nf((int)m, 2) + ":" + nf((int)s, 2), cx, cy);
	}
	
	// This method determines which clock is shown
	public void draw() {
		if(dva % 2 == 0) // dva is iterated based on a 'S' or 's' key press in the keyPressed method
			this.drawAnalog();
		else
			this.drawDigital();
	}
	
	// This method displays the location of the time based on the offset from UTC time
	public void drawLocation() {
		// Sets the formating for the location text
		textSize(55);
		int dlx = 400;
		int dly = 130;
		
		// Switch statement that determines what location each time corresponds to
		switch(offset_hours) {
        case -12 :
           text("Baker Island", dlx, dly); 
           break;
        case -11 :
            text("American Samoa", dlx, dly); 
            break;
        case -10 :
            text("Honolulu", dlx, dly); 
            break;
        case -9 :
            text("Juneau", dlx, dly); 
            break;
        case -8 :
            text("San Francisco", dlx, dly); 
            break;
        case -7 :
            text("Denver", dlx, dly); 
            break;
        case -6 :
            text("Dallas", dlx, dly); 
            break;
        case -5 :
            text("New York", dlx, dly); 
            break;
        case -4 :
            text("Santo Domingo", dlx, dly); 
            break;
        case -3 :
            text("Rio de Janeiro", dlx, dly); 
            break;
         case -2 :
             text("Fernando de Noronha", dlx, dly); 
             break;
         case -1 :
             text("Azores", dlx, dly); 
             break;
         case 0 :
             text("London", dlx, dly); 
             break;
         case 1 :
             text("Paris", dlx, dly); 
             break;
         case 2 :
             text("Cairo", dlx, dly); 
             break;
         case 3 :
             text("Moscow", dlx, dly); 
             break;
         case 4 :
             text("Dubai", dlx, dly); 
             break;
         case 5 :
             text("Karachi", dlx, dly); 
             break;
         case 6 :
             text("Dhaka", dlx, dly); 
             break;
          case 7 :
              text("Jakarta", dlx, dly); 
              break;
          case 8 :
              text("Beijing", dlx, dly); 
              break;
          case 9 :
              text("Tokyo", dlx, dly); 
              break;
          case 10 :
              text("Guam", dlx, dly); 
              break;
          case 11 :
              text("Sydney", dlx, dly); 
              break;
          case 12 :
              text("Marshall Island", dlx, dly); 
              break;
        
		}        
	}
	
	// This method figures out and displays if the time is Am or Pm on the analog clock
	public void drawAmPm() {
		// Sets the formating for the Am or Pm
		textSize(25);
		fill(200);
		
		// Sets the location for the Am or Pm
		int dapx = 475;
		int dapy = 410;
		
		// Displays Am or Pm based on the time
		if (h == 12 || h== 13 || h== 14 || h== 15 || h== 16 || h== 17 || h== 18 || h== 19 || h== 20 || h== 21 || h== 22 || h== 23)
			text("PM" , dapx, dapy);
		else
			text("AM" , dapx, dapy);
		
		// Sets the formating for the box around the Am or Pm
		noFill();
		strokeWeight(3);
		rect(450, 380, 50, 40);
	}
	
	// This method displays the digital clock face
	public void drawDigital() {
		// Sets the formating for the clock
		background(0);
		fill(255);
		
		// Calls the getTime, getDate, display, and drawLocation methods
		this.getTime();
		this.getDate();
		this.display();
		this.drawLocation();
	}
	
	// This method displays the analog clock face
	public void drawAnalog() {
		// Sets the formating for the clock
		background(0);
		fill(255);
		stroke(0);
		strokeWeight(4);
		
		// Creates the clock face by using the ellipse method from the PApplet Class
		ellipse(cx, cy, clockDiameter, clockDiameter);
		
		// Calls the getTime and getDate methods
		getTime();
		getDate();
		
		// Computes the angle each hand should be at a given time
		float s_r = map(s, 0, 60, 0, TWO_PI) - HALF_PI;
		float m_r = map(m + s/60, 0, 60, 0, TWO_PI) - HALF_PI; 
		float h_r = map(h + m/60, 0, 24, 0, TWO_PI * 2) - HALF_PI;
		
		// Creates the hands of the clock based on the angle
		stroke(245,5,5);
		strokeWeight(2);
		line(cx, cy, cx + cos(s_r) * secondsRadius, cy + sin(s_r) * secondsRadius);
		stroke(0);
		strokeWeight(4);
		line(cx, cy, cx + cos(m_r) * minutesRadius, cy + sin(m_r) * minutesRadius);
		strokeWeight(5);
		line(cx, cy, cx + cos(h_r) * hoursRadius, cy + sin(h_r) * hoursRadius);
		
		// Calls the drawLocation and drawAmPm methods
		this.drawLocation();
		this.drawAmPm();

		// Creates the markers around the clock
		fill(0);
		strokeWeight(1);
		for (int a = 0; a < 360; a+=6) {
		float angle = radians(a);
		float x = cx + cos(angle-2) * markerRadius;
		float y = cy + sin(angle-2) * markerRadius;
		
		if (a % 5 == 4) {
		      ellipse(x, y, 6, 6);
		    } else {
		      ellipse(x, y, 3, 3);
		    }
		}
		
		// Prints the the numbers on the clock
		textSize(30);
		fill(0);
		noStroke();
		for (int a = 0; a < 12; a++) {
			float angle = radians(a*30)- (PI/3);
			float x = cx + cos(angle) * secondsRadius;
			float y = cy + sin(angle) * secondsRadius;
			text(a + 1, x, y);
		}
		
		// Creates a cap over the hands
		fill(0);
		stroke(0);
		noStroke();
		ellipse(cx, cy, 17, 17);
	}
}