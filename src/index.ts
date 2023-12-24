import { registerPlugin } from '@capacitor/core';

import type { FacebookEventsPlugin } from './definitions';

const FacebookEvents = registerPlugin<FacebookEventsPlugin>('FacebookEvents', {
  web: () => import('./web').then(m => new m.FacebookEventsWeb()),
});

export * from './definitions';
export { FacebookEvents };
