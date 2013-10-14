/* ********************************************************
 * This file automatically generated by TimeFrame.pl.
 *                   Do not edit.                         *
 **********************************************************/

package uk.ac.starlink.ast;


/**
 * Java interface to the AST TimeFrame class
 *  - time coordinate system description. 
 * A TimeFrame is a specialised form of one-dimensional Frame which
 * represents various coordinate systems used to describe positions in
 * time.
 * <p>
 * A TimeFrame represents a moment in time as either an Modified Julian
 * Date (MJD), a Julian Date (JD), a Besselian epoch or a Julian epoch,
 * as determined by the System attribute. Optionally, a zero point can be
 * specified (using attribute TimeOrigin) which results in the TimeFrame
 * representing time offsets from the specified zero point.
 * <p>
 * Even though JD and MJD are defined as being in units of days, the
 * TimeFrame class allows other units to be used (via the Unit attribute)
 * on the basis of simple scalings (60 seconds = 1 minute, 60 minutes = 1
 * hour, 24 hours = 1 day, 365.25 days = 1 year). Likewise, Julian epochs
 * can be described in units other than the usual years. Besselian epoch
 * are always represented in units of (tropical) years.
 * <p>
 * The TimeScale attribute allows the time scale to be specified (that
 * is, the physical process used to define the rate of flow of time).
 * MJD, JD and Julian epoch can be used to represent a time in any
 * supported time scale. However, Besselian epoch may only be used with the
 * "TT" (Terrestrial Time) time scale. The list of supported time scales
 * includes universal time and siderial time. Strictly, these represent
 * angles rather than time scales, but are included in the list since
 * they are in common use and are often thought of as time scales.
 * <p>
 * When a time value is formatted it can be formated either as a simple
 * floating point value, or as a Gregorian date (see the Format
 * attribute).
 * <h4>Licence</h4>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public Licence as
 * published by the Free Software Foundation; either version 2 of
 * the Licence, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be
 * useful,but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public Licence for more details.
 * <p>
 * You should have received a copy of the GNU General Public Licence
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street,Fifth Floor, Boston, MA
 * 02110-1301, USA
 * 
 * 
 * @see  <a href='http://star-www.rl.ac.uk/cgi-bin/htxserver/sun211.htx/?xref_TimeFrame'>AST TimeFrame</a>  
 */
public class TimeFrame extends Frame {
    /** 
     * Create a TimeFrame.   
     * This function creates a new TimeFrame and optionally initialises
     * its attributes.
     * <p>
     * A TimeFrame is a specialised form of one-dimensional Frame which
     * represents various coordinate systems used to describe positions in
     * time.
     * <p>
     * A TimeFrame represents a moment in time as either an Modified Julian
     * Date (MJD), a Julian Date (JD), a Besselian epoch or a Julian epoch,
     * as determined by the System attribute. Optionally, a zero point can be
     * specified (using attribute TimeOrigin) which results in the TimeFrame
     * representing time offsets from the specified zero point.
     * <p>
     * Even though JD and MJD are defined as being in units of days, the
     * TimeFrame class allows other units to be used (via the Unit attribute)
     * on the basis of simple scalings (60 seconds = 1 minute, 60 minutes = 1
     * hour, 24 hours = 1 day, 365.25 days = 1 year). Likewise, Julian epochs
     * can be described in units other than the usual years. Besselian epoch
     * are always represented in units of (tropical) years.
     * <p>
     * The TimeScale attribute allows the time scale to be specified (that
     * is, the physical proces used to define the rate of flow of time).
     * MJD, JD and Julian epoch can be used to represent a time in any
     * supported time scale. However, Besselian epoch may only be used with the
     * "TT" (Terrestrial Time) time scale. The list of supported time scales
     * includes universal time and siderial time. Strictly, these represent
     * angles rather than time scales, but are included in the list since
     * they are in common use and are often thought of as time scales.
     * <p>
     * When a time value is formatted it can be formated either as a simple
     * floating point value, or as a Gregorian date (see the Format
     * attribute).
     * <h4>Notes</h4>
     * <br> - When conversion between two TimeFrames is requested (as when
     * supplying TimeFrames to astConvert),
     * account will be taken of the nature of the time coordinate systems
     * they represent, together with any qualifying time scale, offset,
     * unit, etc. The AlignSystem and AlignTimeScale attributes will also be
     * taken into account.
     * <br> - A null Object pointer (AST__NULL) will be returned if this
     * function is invoked with the AST error status set, or if it
     * should fail for any reason.
     * @throws  AstException  if an error occurred in the AST library
    */
    public TimeFrame(  ) {
        construct(  );
    }
    private native void construct(  );

    /** 
     * Return the current system time.   
     * This function
     * returns the current system time, represented in the form specified
     * by the supplied TimeFrame. That is, the returned floating point
     * value should be interpreted using the attribute values of the
     * TimeFrame. This includes System, TimeOrigin, LTOffset, TimeScale,
     * and Unit.
     * <h4>Notes</h4>
     * <br> - Values of AST__BAD will be returned if this function is
     * invoked with the AST error status set, or if it should fail for
     * any reason.
     * <br> - It is assumes that the system time (returned by the C time()
     * function) follows the POSIX standard, representing a continuous
     * monotonic increasing count of SI seconds since the epoch 00:00:00
     * UTC 1 January 1970 AD (equivalent to TAI with a constant offset).
     * Resolution is one second.
     * <br> - An error will be reported if the TimeFrame has a TimeScale value
     * which cannot be converted to TAI (e.g. "angular" systems such as
     * UT1, GMST, LMST and LAST).
     * <br> - Any inaccuracy in the system clock will be reflected in the value
     * returned by this function.
     * @return  A TimeFrame axis value representing the current system time.
     * 
     * @throws  AstException  if an error occurred in the AST library
     */
    public native double currentTime(  );

    /**
     * Get 
     * time scale to use when aligning TimeFrames.  
     * This attribute controls how a TimeFrame behaves when it is used (by
     * astFindFrame or astConvert) as a template to match another (target)
     * TimeFrame. It identifies the time scale in which alignment is
     * to occur. See the TimeScale attribute for a desription of the values
     * which may be assigned to this attribute. The default AlignTimeScale
     * value depends on the current value of TimeScale: if TimeScale is
     * UT1, GMST, LMST or LAST, the default for AlignTimeScale is UT1, for all
     * other TimeScales the default is TAI.
     * <p>
     * When astFindFrame or astConvert is used on two TimeFrames (potentially
     * describing different time coordinate systems), it returns a Mapping
     * which can be used to transform a position in one TimeFrame into the
     * corresponding position in the other. The Mapping is made up of the
     * following steps in the indicated order:
     * <p>
     * <br> - Map values from the system used by the target (MJD, JD, etc) to the
     * system specified by the AlignSystem attribute.
     * <p>
     * <br> - Map these values from the target's time scale to the time scale
     * specified by the AlignTimeScale attribute.
     * <p>
     * <br> - Map these values from the time scale specified by the AlignTimeScale
     * attribute, to the template's time scale.
     * <p>
     * <br> - Map these values from the system specified by the AlignSystem
     * attribute, to the system used by the template.
     * 
     *
     * @return  this object's AlignTimeScale attribute
     */
    public String getAlignTimeScale() {
        return getC( "AlignTimeScale" );
    }

    /**
     * Set 
     * time scale to use when aligning TimeFrames.  
     * This attribute controls how a TimeFrame behaves when it is used (by
     * astFindFrame or astConvert) as a template to match another (target)
     * TimeFrame. It identifies the time scale in which alignment is
     * to occur. See the TimeScale attribute for a desription of the values
     * which may be assigned to this attribute. The default AlignTimeScale
     * value depends on the current value of TimeScale: if TimeScale is
     * UT1, GMST, LMST or LAST, the default for AlignTimeScale is UT1, for all
     * other TimeScales the default is TAI.
     * <p>
     * When astFindFrame or astConvert is used on two TimeFrames (potentially
     * describing different time coordinate systems), it returns a Mapping
     * which can be used to transform a position in one TimeFrame into the
     * corresponding position in the other. The Mapping is made up of the
     * following steps in the indicated order:
     * <p>
     * <br> - Map values from the system used by the target (MJD, JD, etc) to the
     * system specified by the AlignSystem attribute.
     * <p>
     * <br> - Map these values from the target's time scale to the time scale
     * specified by the AlignTimeScale attribute.
     * <p>
     * <br> - Map these values from the time scale specified by the AlignTimeScale
     * attribute, to the template's time scale.
     * <p>
     * <br> - Map these values from the system specified by the AlignSystem
     * attribute, to the system used by the template.
     * 
     *
     * @param  alignTimeScale   the AlignTimeScale attribute of this object
     */
    public void setAlignTimeScale( String alignTimeScale ) {
       setC( "AlignTimeScale", alignTimeScale );
    }

    /**
     * Get 
     * the offset from UTC to Local Time, in hours.  
     * This specifies the offset from UTC to Local Time, in hours (fractional
     * hours can be supplied). It is positive for time zones east of Greenwich.
     * AST uses the figure as given, without making any attempt to correct for
     * daylight saving. The default value is zero.
     * 
     *
     * @return  this object's LtOffset attribute
     */
    public double getLtOffset() {
        return getD( "LtOffset" );
    }

    /**
     * Set 
     * the offset from UTC to Local Time, in hours.  
     * This specifies the offset from UTC to Local Time, in hours (fractional
     * hours can be supplied). It is positive for time zones east of Greenwich.
     * AST uses the figure as given, without making any attempt to correct for
     * daylight saving. The default value is zero.
     * 
     *
     * @param  ltOffset   the LtOffset attribute of this object
     */
    public void setLtOffset( double ltOffset ) {
       setD( "LtOffset", ltOffset );
    }

    /**
     * Get 
     * the zero point for TimeFrame axis values.  
     * This specifies the origin from which all time values are measured.
     * The default value (zero) results in the TimeFrame describing
     * absolute time values in the system given by the System attribute
     * (e.g. MJD, Julian epoch, etc). If a TimeFrame is to be used to
     * describe elapsed time since some origin, the TimeOrigin attribute
     * should be set to hold the required origin value. The TimeOrigin value
     * stored inside the TimeFrame structure is modified whenever TimeFrame
     * attribute values are changed so that it refers to the original moment
     * in time.
     * <h4>Input Formats</h4>
     * The formats accepted when setting a TimeOrigin value are listed
     * below. They are all case-insensitive and are generally tolerant
     * of extra white space and alternative field delimiters:
     * <p>
     * <br> - Besselian Epoch: Expressed in decimal years, with or without
     * decimal places ("B1950" or "B1976.13" for example).
     * <p>
     * <br> - Julian Epoch: Expressed in decimal years, with or without
     * decimal places ("J2000" or "J2100.9" for example).
     * <p>
     * <br> - Units: An unqualified decimal value is interpreted as a value in
     * the system specified by the TimeFrame's System attribute, in the
     * units given by the TimeFrame's Unit attribute. Alternatively, an
     * appropriate unit string can be appended to the end of the floating
     * point value ("123.4 d" for example), in which case the supplied value
     * is scaled into the units specified by the Unit attribute.
     * <p>
     * <br> - Julian Date: With or without decimal places ("JD 2454321.9" for
     * example).
     * <p>
     * <br> - Modified Julian Date: With or without decimal places
     * ("MJD 54321.4" for example).
     * <p>
     * <br> - Gregorian Calendar Date: With the month expressed either as an
     * integer or a 3-character abbreviation, and with optional decimal
     * places to represent a fraction of a day ("1996-10-2" or
     * "1996-Oct-2.6" for example). If no fractional part of a day is
     * given, the time refers to the start of the day (zero hours).
     * <p>
     * <br> - Gregorian Date and Time: Any calendar date (as above) but with
     * a fraction of a day expressed as hours, minutes and seconds
     * ("1996-Oct-2 12:13:56.985" for example). The date and time can be
     * separated by a space or by a "T" (as used by ISO8601 format).
     * <h4>Output Format</h4>
     * When enquiring TimeOrigin values, the returned formatted floating
     * point value represents a value in the TimeFrame's System, in the unit
     * specified by the TimeFrame's Unit attribute.
     * 
     *
     * @return  this object's TimeOrigin attribute
     */
    public double getTimeOrigin() {
        return getD( "TimeOrigin" );
    }

    /**
     * Set 
     * the zero point for TimeFrame axis values.  
     * This specifies the origin from which all time values are measured.
     * The default value (zero) results in the TimeFrame describing
     * absolute time values in the system given by the System attribute
     * (e.g. MJD, Julian epoch, etc). If a TimeFrame is to be used to
     * describe elapsed time since some origin, the TimeOrigin attribute
     * should be set to hold the required origin value. The TimeOrigin value
     * stored inside the TimeFrame structure is modified whenever TimeFrame
     * attribute values are changed so that it refers to the original moment
     * in time.
     * <h4>Input Formats</h4>
     * The formats accepted when setting a TimeOrigin value are listed
     * below. They are all case-insensitive and are generally tolerant
     * of extra white space and alternative field delimiters:
     * <p>
     * <br> - Besselian Epoch: Expressed in decimal years, with or without
     * decimal places ("B1950" or "B1976.13" for example).
     * <p>
     * <br> - Julian Epoch: Expressed in decimal years, with or without
     * decimal places ("J2000" or "J2100.9" for example).
     * <p>
     * <br> - Units: An unqualified decimal value is interpreted as a value in
     * the system specified by the TimeFrame's System attribute, in the
     * units given by the TimeFrame's Unit attribute. Alternatively, an
     * appropriate unit string can be appended to the end of the floating
     * point value ("123.4 d" for example), in which case the supplied value
     * is scaled into the units specified by the Unit attribute.
     * <p>
     * <br> - Julian Date: With or without decimal places ("JD 2454321.9" for
     * example).
     * <p>
     * <br> - Modified Julian Date: With or without decimal places
     * ("MJD 54321.4" for example).
     * <p>
     * <br> - Gregorian Calendar Date: With the month expressed either as an
     * integer or a 3-character abbreviation, and with optional decimal
     * places to represent a fraction of a day ("1996-10-2" or
     * "1996-Oct-2.6" for example). If no fractional part of a day is
     * given, the time refers to the start of the day (zero hours).
     * <p>
     * <br> - Gregorian Date and Time: Any calendar date (as above) but with
     * a fraction of a day expressed as hours, minutes and seconds
     * ("1996-Oct-2 12:13:56.985" for example). The date and time can be
     * separated by a space or by a "T" (as used by ISO8601 format).
     * <h4>Output Format</h4>
     * When enquiring TimeOrigin values, the returned formatted floating
     * point value represents a value in the TimeFrame's System, in the unit
     * specified by the TimeFrame's Unit attribute.
     * 
     *
     * @param  timeOrigin   the TimeOrigin attribute of this object
     */
    public void setTimeOrigin( double timeOrigin ) {
       setD( "TimeOrigin", timeOrigin );
    }

   /**
    * Set 
     * the zero point for TimeFrame axis values. 
     * This specifies the origin from which all time values are measured.
     * The default value (zero) results in the TimeFrame describing
     * absolute time values in the system given by the System attribute
     * (e.g. MJD, Julian epoch, etc). If a TimeFrame is to be used to
     * describe elapsed time since some origin, the TimeOrigin attribute
     * should be set to hold the required origin value. The TimeOrigin value
     * stored inside the TimeFrame structure is modified whenever TimeFrame
     * attribute values are changed so that it refers to the original moment
     * in time.
     * <h4>Input Formats</h4>
     * The formats accepted when setting a TimeOrigin value are listed
     * below. They are all case-insensitive and are generally tolerant
     * of extra white space and alternative field delimiters:
     * <p>
     * <br> - Besselian Epoch: Expressed in decimal years, with or without
     * decimal places ("B1950" or "B1976.13" for example).
     * <p>
     * <br> - Julian Epoch: Expressed in decimal years, with or without
     * decimal places ("J2000" or "J2100.9" for example).
     * <p>
     * <br> - Units: An unqualified decimal value is interpreted as a value in
     * the system specified by the TimeFrame's System attribute, in the
     * units given by the TimeFrame's Unit attribute. Alternatively, an
     * appropriate unit string can be appended to the end of the floating
     * point value ("123.4 d" for example), in which case the supplied value
     * is scaled into the units specified by the Unit attribute.
     * <p>
     * <br> - Julian Date: With or without decimal places ("JD 2454321.9" for
     * example).
     * <p>
     * <br> - Modified Julian Date: With or without decimal places
     * ("MJD 54321.4" for example).
     * <p>
     * <br> - Gregorian Calendar Date: With the month expressed either as an
     * integer or a 3-character abbreviation, and with optional decimal
     * places to represent a fraction of a day ("1996-10-2" or
     * "1996-Oct-2.6" for example). If no fractional part of a day is
     * given, the time refers to the start of the day (zero hours).
     * <p>
     * <br> - Gregorian Date and Time: Any calendar date (as above) but with
     * a fraction of a day expressed as hours, minutes and seconds
     * ("1996-Oct-2 12:13:56.985" for example). The date and time can be
     * separated by a space or by a "T" (as used by ISO8601 format).
     * <h4>Output Format</h4>
     * When enquiring TimeOrigin values, the returned formatted floating
     * point value represents a value in the TimeFrame's System, in the unit
     * specified by the TimeFrame's Unit attribute.
     * 
    *
    * @param  timeOrigin  formatted string giving the TimeOrigin 
    *          attribute of this object
    */
   public void setTimeOrigin( String timeOrigin ) {
      setC( "TimeOrigin", timeOrigin );
   }

    /**
     * Get 
     * time scale.  
     * This attribute identifies the time scale to which the time axis values
     * of a TimeFrame refer, and may take any of the values listed in the
     * "Time Scales" section (below).
     * <p>
     * The default TimeScale value depends on the current System value; if
     * the current TimeFrame system is "Besselian epoch" the default is
     * "TT", otherwise it is "TAI". Note, if the System attribute is set
     * so that the TimeFrame represents Besselian Epoch, then an error
     * will be reported if an attempt is made to set the TimeScale to
     * anything other than TT.
     * <p>
     * Note, the supported time scales fall into two groups. The first group
     * containing  UT1, GMST, LAST and LMST define time in terms of the
     * orientation of the earth. The second group (containing all the remaining
     * time scales) define time in terms of an atomic process. Since the rate of
     * rotation of the earth varies in an unpredictable way, conversion between
     * two timescales in different groups relies on a value being supplied for
     * the Dut1 attribute (defined by the parent Frame class). This attribute
     * specifies the difference between the UT1 and UTC time scales, in seconds,
     * and defaults to zero. See the documentation for the Dut1 attribute for
     * further details.
     * <h4>Time Scales</h4>
     * The TimeFrame class supports the following TimeScale values (all are
     * case-insensitive):
     * <p>
     * <br> - "TAI" - International Atomic Time
     * <br> - "UTC" - Coordinated Universal Time
     * <br> - "UT1" - Universal Time
     * <br> - "GMST" - Greenwich Mean Sidereal Time
     * <br> - "LAST" - Local Apparent Sidereal Time
     * <br> - "LMST" - Local Mean Sidereal Time
     * <br> - "TT" - Terrestrial Time
     * <br> - "TDB" - Barycentric Dynamical Time
     * <br> - "TCB" - Barycentric Coordinate Time
     * <br> - "TCG" - Geocentric Coordinate Time
     * <br> - "LT" - Local Time (the offset from UTC is given by attribute LTOffset)
     * <p>
     * An very informative description of these and other time scales is
     * available at http://www.ucolick.org/~sla/leapsecs/timescales.html.
     * <h4>UTC Warnings</h4>
     * UTC should ideally be expressed using separate hours, minutes and
     * seconds fields (or at least in seconds for a given date) if leap seconds
     * are to be taken into account. Since the TimeFrame class represents
     * each moment in time using a single floating point number (the axis value)
     * there will be an ambiguity during a leap second. Thus an error of up to
     * 1 second can result when using AST to convert a UTC time to another
     * time scale if the time occurs within a leap second. Leap seconds
     * occur at most twice a year, and are introduced to take account of
     * variation in the rotation of the earth. The most recent leap second
     * occurred on 1st January 1999. Although in the vast majority of cases
     * leap second ambiguities won't matter, there are potential problems in
     * on-line data acquisition systems and in critical applications involving
     * taking the difference between two times.
     * 
     *
     * @return  this object's TimeScale attribute
     */
    public String getTimeScale() {
        return getC( "TimeScale" );
    }

    /**
     * Set 
     * time scale.  
     * This attribute identifies the time scale to which the time axis values
     * of a TimeFrame refer, and may take any of the values listed in the
     * "Time Scales" section (below).
     * <p>
     * The default TimeScale value depends on the current System value; if
     * the current TimeFrame system is "Besselian epoch" the default is
     * "TT", otherwise it is "TAI". Note, if the System attribute is set
     * so that the TimeFrame represents Besselian Epoch, then an error
     * will be reported if an attempt is made to set the TimeScale to
     * anything other than TT.
     * <p>
     * Note, the supported time scales fall into two groups. The first group
     * containing  UT1, GMST, LAST and LMST define time in terms of the
     * orientation of the earth. The second group (containing all the remaining
     * time scales) define time in terms of an atomic process. Since the rate of
     * rotation of the earth varies in an unpredictable way, conversion between
     * two timescales in different groups relies on a value being supplied for
     * the Dut1 attribute (defined by the parent Frame class). This attribute
     * specifies the difference between the UT1 and UTC time scales, in seconds,
     * and defaults to zero. See the documentation for the Dut1 attribute for
     * further details.
     * <h4>Time Scales</h4>
     * The TimeFrame class supports the following TimeScale values (all are
     * case-insensitive):
     * <p>
     * <br> - "TAI" - International Atomic Time
     * <br> - "UTC" - Coordinated Universal Time
     * <br> - "UT1" - Universal Time
     * <br> - "GMST" - Greenwich Mean Sidereal Time
     * <br> - "LAST" - Local Apparent Sidereal Time
     * <br> - "LMST" - Local Mean Sidereal Time
     * <br> - "TT" - Terrestrial Time
     * <br> - "TDB" - Barycentric Dynamical Time
     * <br> - "TCB" - Barycentric Coordinate Time
     * <br> - "TCG" - Geocentric Coordinate Time
     * <br> - "LT" - Local Time (the offset from UTC is given by attribute LTOffset)
     * <p>
     * An very informative description of these and other time scales is
     * available at http://www.ucolick.org/~sla/leapsecs/timescales.html.
     * <h4>UTC Warnings</h4>
     * UTC should ideally be expressed using separate hours, minutes and
     * seconds fields (or at least in seconds for a given date) if leap seconds
     * are to be taken into account. Since the TimeFrame class represents
     * each moment in time using a single floating point number (the axis value)
     * there will be an ambiguity during a leap second. Thus an error of up to
     * 1 second can result when using AST to convert a UTC time to another
     * time scale if the time occurs within a leap second. Leap seconds
     * occur at most twice a year, and are introduced to take account of
     * variation in the rotation of the earth. The most recent leap second
     * occurred on 1st January 1999. Although in the vast majority of cases
     * leap second ambiguities won't matter, there are potential problems in
     * on-line data acquisition systems and in critical applications involving
     * taking the difference between two times.
     * 
     *
     * @param  timeScale   the TimeScale attribute of this object
     */
    public void setTimeScale( String timeScale ) {
       setC( "TimeScale", timeScale );
    }

    /**
     * Synonym for {@link #setObsLat}.
     */ 
    public void setClockLat( String clockLat ) {
        setObsLat( clockLat );
    } 

    /**
     * Synonym for {@link #getObsLat}.
     */
    public String getClockLat() {
        return getObsLat();
    }

    /**
     * Synonym for {@link #setObsLon}.
     */
    public void setClockLon( String clockLon ) {
        setObsLon( clockLon );
    }

    /**
     * Synonym for {@link #getObsLon}.
     */ 
    public String getClockLon() {
        return getObsLon();
    } 
}
