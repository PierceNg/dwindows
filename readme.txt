This is a stable release of Dynamic Windows version 2.3.

The current Dynamic Windows source base is considered stable on:
OS/2, Mac, Windows, Linux, FreeBSD and Solaris.

Known problems:

Boxes with no expandable items will have their contents centered on 
    GTK2 instead of top or left justified on the other platforms.
GTK3 due to changes in the core architecture does not support
    widgets that are smaller than what is contained within them
    unless they use scrolled windows. GTK2 and other platforms do.

Known limitations:

It is not safe on all platforms to operate on widgets before they
are packed.  For portability pack widgets before operating on them.

Future features:

OS/2 is currently missing the HTML widget because the system does 
not support it by default. Looking into importing functionality 
from available libraries (Firefox, Webkit, Qt, etc).

Changes from version 2.2:
Added dw_menu_delete_item() for removing menu items.
    Now you can use dw_window_destroy() on menu items handles also.
Added dw_app_dir() which attempts to locate private application data
    for the current platform.
Added dw_window_set_tooltip() for adding tooltips to most widgets.
Added dw_window_get_preferred_size() for getting the size the system
    thinks the widget should be, to aid in creating layouts.
Added support for -1 size parameter to the dw_box_pack*() functions to 
    automatically figure out a suggested size for many controls.
Added automatic window redraw support for OS/2, Windows and Mac.
Added dw_window_set_gravity() for enhanced window placement.
Added DW_GRAV_OBSTACLES support for using system obstacles as gravity
   anchor points on OS/2, Mac, Windows and GTK3 (3.4 and later).
Added automatic window border detection on GTK.
   This eliminates the need for the DW_BORDER_* environment variables.
Added Open Watcom compiler support for OS/2 (Thanks Andy Willis).
Added pseudo transparent background widget support on Windows and OS/2.
   If you set the background color of a widget to DW_RGB_TRANSPARENT...
   it will attempt to use the background color of the parent.
   Several types of widgets have this set by default now.
Added faster and less buggy layout engine 2.0 for OS/2, Windows and Mac.
Added auto-positioning on Mac; reimplemented auto-positioning on OS/2.
Added DW_DRAW_NOAA which can be passed to dw_draw_*() API functions
   that accept the flags parameter to disable anti-aliasing.
Added dw_main_quit() to cause the dw_main() message loop to return.
Added DW_FCF_COMPOSITED to enable Aero Glass effects on Windows 7 and Vista.
Added local autorelease pools on Mac making _dw_pool_drain() unnecessary.
Fixed auto-sizing top-level windows on Windows; added on OS/2 and Mac.
Fixed bubble help not being displayed on Windows.
Fixed menu bar items remaining highlighted on Mac 10.6 and later.
Fixed OS/2 and Windows showing windows during dw_window_set_pos/size().
Fixed OS/2 scrollbox resizing problems when not in the initial position.
Fixed Windows scrollbox maximum position is off by one.
Fixed various GTK3 issues and removed all deprecated functions for 3.4.
Fixed MLE issues on almost all platforms.
Removed broken support for GTK 3.0, GTK 3.2 or later is required now.
Deprecated DW_FCF_NOBYTEALIGN, DW_FCF_SHELLPOSITION, DW_FCF_HORZSCROLL and
   DW_FCF_VERTSCROLL flags.
Renamed compat.c and compat.h to dwcompat.c and dwcompat.h respectively.

Dynamic Windows Documentation is available at:

http://dbsoft.org/dw_help.php

If you have any questions or suggestions feel free to email me at:

brian@dbsoft.org

Thanks!

Brian Smith